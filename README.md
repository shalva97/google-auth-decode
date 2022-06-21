# About

This library will help you to decode Google Auth's URI which looks like this

```
otpauth-migration://offline?data=ChsKApHREgphc3M6dG9pbGV0GgNhc3MgASgBMAIQARgBIAAo05KP1%2F3%2F%2F%2F%2F%2FAQ%3D%3D
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
        implementation 'com.github.shalva97:google-auth-decode:0.0.7'
    }

# Usage

There is an extension `fun String.decodeGoogleAuthMigrationURI()`, which you can use like this

```kotlin
val listOfOTPs =
    "otpauth-migration://offline?data=ChsKApHREgphc3M6dG9pbGV0GgNhc3MgASgBMAIQARgBIAAo05KP1%2F3%2F%2F%2F%2F%2FAQ%3D%3D"
        .decodeGoogleAuthMigrationURI()
listOfOTPs // will be a list of all the OTP data contained in the URI

// java

String uriText = "otpauth-migration://...";
var listOfOTPs = GoogleAuthDecoderKt.decodeGoogleAuthMigrationURI(uriText);

```

# CLI

decode "otp-data-here"

# Similar projects

- https://rootprojects.org/authenticator/
- https://github.com/dim13/otpauth
- https://github.com/digitalduke/otpauth-migration-decoder
- https://github.com/scito/extract_otp_secret_keys
- https://github.com/EasyG0ing1/GoogleAuthDecoder
- Other projects: https://github.com/topics/google-authenticator


- https://keepachangelog.com/