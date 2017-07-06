package com.synco.backup

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.InputStreamContent
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.drive.Drive
import com.google.api.services.drive.model.ParentReference
import com.synco.admin.SettingsService
import com.synco.domain.File
import com.synco.domain.GoogleDriveLocation
import org.springframework.stereotype.Service
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import javax.annotation.PostConstruct
import javax.crypto.Cipher
import javax.crypto.CipherInputStream
import javax.crypto.spec.SecretKeySpec
import javax.crypto.spec.IvParameterSpec


@Service
class GoogleDriveBackupServiceImpl(val settingsService: SettingsService) : BackupService<GoogleDriveLocation> {
    private var drive: Drive? = null

    override fun save(file: File): GoogleDriveLocation {
        val token = settingsService.getGoogleToken()
        if (token != null) {
            drive = Drive.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    JacksonFactory.getDefaultInstance(),
                    GoogleCredential().setAccessToken(token.token))
                    .setApplicationName("Synco")
                    .build()
//            val key = SecretKeySpec("mySuperSecretKey".toByteArray(), "AES")

            val cipher = getCipherEncrypt("mySuperSecretKey".toByteArray())
            val encryptedIs = CipherInputStream(file.inputStream, cipher)
//            val mediaContent = InputStreamContent("application/octet-stream", file.inputStream)
            val byte = ByteArrayOutputStream()
            encryptedIs.copyTo(byte)

            if (token.folderId == null) {
                val fileMetadata = com.google.api.services.drive.model.File()
                fileMetadata.title = "synco__files"
                fileMetadata.mimeType = "application/vnd.google-apps.folder"

                val folder = (drive as Drive).files().insert(fileMetadata)
                        .setFields("id")
                        .execute()

                token.folderId = folder.id
            }

            val gDriveFile = com.google.api.services.drive.model.File()
            gDriveFile.title = file.metadata.name + ".zip"
            gDriveFile.parents = listOf(ParentReference().setId(token.folderId))

            val mediaContent = InputStreamContent("application/octet-stream", ByteArrayInputStream(byte.toByteArray()))
            mediaContent.length = byte.toByteArray().size.toLong()
            val savedFile = (drive as Drive).files().insert(gDriveFile, mediaContent)
                    .setFields("id").execute()

            return GoogleDriveLocation(fileMetadata = file.metadata, driveId = savedFile.id)
        }
        return GoogleDriveLocation()
    }

    override fun retrieve(location: GoogleDriveLocation): File {
        try {
            val token = settingsService.getGoogleToken()
            if (token != null) {
                drive = Drive.Builder(
                        GoogleNetHttpTransport.newTrustedTransport(),
                        JacksonFactory.getDefaultInstance(),
                        GoogleCredential().setAccessToken(token.token))
                        .setApplicationName("Synco")
                        .build()
                val outputStream = ByteArrayOutputStream()
                (drive as Drive).files().get(location.driveId)
                        .executeMediaAndDownloadTo(outputStream)


                val cipher = getCipherDecrypt("mySuperSecretKey".toByteArray())
                val decrypted = CipherInputStream(ByteArrayInputStream(outputStream.toByteArray()), cipher)

                return File(location.fileMetadata, decrypted)
            }
        } catch (e: IOException) {
            println(e)
            return File(location.fileMetadata, ByteArrayInputStream(ByteArray(0)))
        }
        return File(location.fileMetadata, ByteArrayInputStream(ByteArray(0)))
    }


    @Throws(Exception::class)
    fun getCipherEncrypt(key: ByteArray): Cipher {
        val keyBytes = getKeyBytes(key)
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val secretKeySpec = SecretKeySpec(keyBytes, "AES")
        val ivParameterSpec = IvParameterSpec(keyBytes)
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec)
        return cipher
    }

    @Throws(Exception::class)
    fun getCipherDecrypt(key: ByteArray): Cipher {
        val keyBytes = getKeyBytes(key)
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val secretKeySpec = SecretKeySpec(keyBytes, "AES")
        val ivParameterSpec = IvParameterSpec(keyBytes)
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec)
        return cipher
    }

    @Throws(Exception::class)
    private fun getKeyBytes(key: ByteArray): ByteArray {
        val keyBytes = ByteArray(16)
        System.arraycopy(key, 0, keyBytes, 0, Math.min(key.size, keyBytes.size))
        return keyBytes
    }
}