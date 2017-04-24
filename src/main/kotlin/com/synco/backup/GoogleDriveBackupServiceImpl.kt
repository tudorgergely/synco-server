package com.synco.backup

import com.synco.domain.File
import com.synco.domain.GoogleDriveLocation
import java.io.FileInputStream
import java.io.ObjectInputStream
import javax.crypto.Cipher
import javax.crypto.CipherInputStream
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.DESKeySpec
import com.google.api.services.drive.Drive
import java.io.BufferedInputStream
import com.google.api.client.http.InputStreamContent




class GoogleDriveBackupServiceImpl : BackupService<GoogleDriveLocation> {
    override fun save(file: File): GoogleDriveLocation {
        val ois = ObjectInputStream(FileInputStream("keyfile"))
        val ks = DESKeySpec(ois.readObject() as ByteArray)
        val skf = SecretKeyFactory.getInstance("AES")
        val key = skf.generateSecret(ks)

        val cipher = Cipher.getInstance("AES/CBC/NoPadding")
        cipher.init(Cipher.ENCRYPT_MODE, key);
        val encryptedIs = CipherInputStream(file.inputStream, cipher)
        val mediaContent = InputStreamContent("image/jpeg", encryptedIs)

        mediaContent.length = file.metadata.size.toLong()
        val drive = Drive(null, null, null)
        val request = drive.files().insert(com.google.api.services.drive.model.File(), mediaContent)
        request.execute()
        return GoogleDriveLocation()
    }

    override fun retrieve(location: GoogleDriveLocation): File {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}