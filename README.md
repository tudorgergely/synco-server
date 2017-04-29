SYNCO
-----
##### Server which syncs your files with search capabilities

## How to install

### Prerequisites
Running
 - JRE (for running packaged jar version)
 
```
java -jar packages/synco-server.jar
```
 
Development
 - JDK 8 
 - [Gradle](https://gradle.org/)
 
### Installation (Development)

```
$ git clone https://github.com/tudorgergely/synco-server.git
$ cd synco-server
$ gradle clean build --refresh-dependencies
$ gradle bootRun
```

