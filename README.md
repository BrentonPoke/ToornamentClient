[![Release](https://jitpack.io/v/AreaFiftyLAN/toornament-client.svg)](https://jitpack.io/AreaFiftyLAN/toornament-client)
# Toornament client

A java api client for Toornament. It's in very early development, but sort of usable. Expect a lot of (breaking) changes as we implement features. You're most welcome to help if you require a feature that's not implemented yet. Feel free to open an issue or pull request! We aim to respond / review within a few days.


## Add to Gradle:
To use the current master branch in your project, add this to your build.gradle
```
allprojects {
        repositories {
            jcenter()
            maven { url "https://jitpack.io" }
        }
   }
   dependencies {
        compile 'com.github.AreaFiftyLAN:toornament-client:master-SNAPSHOT''
   }
```
This will add a snapshot of the current master to your classpath. Note that this will update very regularly, and can break. Once we reached a somewhat significant amount of features, we'll change to release based releases. Use with caution, not recommended for production systems quite yet!

## Terms and Guidelines

The same terms and conditions apply as for the Toornament API, as stated on their website: https://developer.toornament.com/terms-guidelines

This API client is licenced under the MIT License, which can be found in this repository for reference.
