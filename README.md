# About [![](https://jitpack.io/v/shalva97/google-auth-decode.svg)](https://jitpack.io/#shalva97/google-auth-decode)

This library will help you to decode Google Auth's URI which looks like this

```
otpauth-migration://offline?data=ChsKApHREgphc3M6dG9pbGV0GgNhc3MgASgBMAIQARgBIAAo05KP1%2F3%2F%2F%2F%2F%2FAQ%3D%3D
```

# CLI

Download the executables and run them or use the web version from about section. For example download jar from the latest release page and run it:

```bash
$ java -jar google-auth-decode-0.0.12-all.jar "otpauth-migration://offline?data=Cj8KFD3GyqSCSm0oh2eyMx4gtDFmy4XZEhhBQ01FIENvOmpvaG5AZXhhbXBsZS5jb20aB0FDTUUgQ28gASgBMAIKWQoUmWnif8JDwjlbrmF6aCME%2FSEWkVkSJUtvdGxpbiBnb29kIGphdmEgYmFkOmpvaG5AZXhhbXBsZS5jb20aFEtvdGxpbiBnb29kIGphdmEgYmFkIAEoATACCjkKFE62Apiv1fDVj9%2Fm5dEtIK60BBmfEhVCbGFoOmpvaG5AZXhhbXBsZS5jb20aBEJsYWggASgBMAIKOQoU%2FPHoXjuc6zFhB5ahA9TzNUCu7SwSFWFzZGY6am9obkBleGFtcGxlLmNvbRoEYXNkZiABKAEwAhABGAEgACiHg9OF%2Bf%2F%2F%2F%2F8B"
OTPData(name=ACME Co:john@example.com, algorithm=ALGORITHM_SHA1, issuer=ACME Co, secret=HXDMVJECJJWSRB3HWIZR4IFUGFTMXBOZ, type=TOTP)
OTPData(name=Kotlin good java bad:john@example.com, algorithm=ALGORITHM_SHA1, issuer=Kotlin good java bad, secret=TFU6E76CIPBDSW5OMF5GQIYE7UQRNEKZ, type=TOTP)
OTPData(name=Blah:john@example.com, algorithm=ALGORITHM_SHA1, issuer=Blah, secret=J23AFGFP2XYNLD6743S5CLJAV22AIGM7, type=TOTP)
OTPData(name=asdf:john@example.com, algorithm=ALGORITHM_SHA1, issuer=asdf, secret=7TY6QXR3TTVTCYIHS2QQHVHTGVAK53JM, type=TOTP)
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

    implementation("com.github.shalva97:google-auth-decode:0.0.16")

# Usage

There is an extension `fun String.decodeGoogleAuthMigrationURI()`, which you can use like this

```kotlin
val listOfOTPs = "otpauth-migration://offline?data=...".decodeGoogleAuthMigrationURI()
listOfOTPs // will be a list of all the OTP data contained in the URI

// java
String uriText = "otpauth-migration://offline?data=...";
var listOfOTPs = GoogleAuthDecoderKt.decodeGoogleAuthMigrationURI(uriText);

```

# Build from source

clone the repository and in the project directory run `./gradlew shadowjar`
then inside `build/libs` folder should be a jar file which you can run
with `java -jar filename-all.jar "otpauth-..."`

# Similar projects

- https://rootprojects.org/authenticator/
- https://github.com/dim13/otpauth
- https://github.com/digitalduke/otpauth-migration-decoder
- https://github.com/scito/extract_otp_secret_keys
- https://github.com/EasyG0ing1/GoogleAuthDecoder
- Other projects: https://github.com/topics/google-authenticator
