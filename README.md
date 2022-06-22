# About [![](https://jitpack.io/v/shalva97/google-auth-decode.svg)](https://jitpack.io/#shalva97/google-auth-decode)

This library will help you to decode Google Auth's URI which looks like this

```
otpauth-migration://offline?data=ChsKApHREgphc3M6dG9pbGV0GgNhc3MgASgBMAIQARgBIAAo05KP1%2F3%2F%2F%2F%2F%2FAQ%3D%3D
```

# CLI

Download the jar from the latest release page and run it:

```bash
java -jar google-auth-decode-0.0.10-all.jar "otpauth-migration://offline?data=..."
```

# Install

Step 1. Add it in your root build.gradle at the end of repositories:

    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }

or in `settings.gradle`

```kotlin
dependencyResolutionManagement {
    ...
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```

Step 2. Add the dependency

    dependencies {
        implementation("com.github.shalva97:google-auth-decode:0.0.10")
    }

# Usage

There is an extension `fun String.decodeGoogleAuthMigrationURI()`, which you can use like this

```kotlin
val listOfOTPs = "otpauth-migration://offline?data=...".decodeGoogleAuthMigrationURI()
listOfOTPs // will be a list of all the OTP data contained in the URI

// java
String uriText = "otpauth-migration://offline?data=...";
var listOfOTPs = GoogleAuthDecoderKt.decodeGoogleAuthMigrationURI(uriText);

```

# Similar projects

- https://rootprojects.org/authenticator/
- https://github.com/dim13/otpauth
- https://github.com/digitalduke/otpauth-migration-decoder
- https://github.com/scito/extract_otp_secret_keys
- https://github.com/EasyG0ing1/GoogleAuthDecoder
- Other projects: https://github.com/topics/google-authenticator


- https://keepachangelog.com/