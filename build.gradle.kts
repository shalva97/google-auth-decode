import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.plugin.mpp.BitcodeEmbeddingMode.BITCODE
import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG
import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.RELEASE

plugins {
    kotlin("multiplatform") version "1.7.0"
    kotlin("plugin.serialization") version "1.7.10"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("java")
    id("com.adarshr.test-logger") version "3.2.0"
    `maven-publish`
    kotlin("native.cocoapods") version "1.7.0"
}

group = "com.github.shalva97"
version = "0.0.16"

kotlin {
    jvm {
        val main by compilations.getting {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }
    mingwX64 {
        binaries {
            executable {
                entryPoint = "com.github.shalva97.main"
                baseName = "google-auth-decode-$version-mingwX64"
            }
        }
    }

    macosX64 {
        binaries {
            executable {
                entryPoint = "com.github.shalva97.main"
                baseName = "google-auth-decode-$version-macosX64"
            }
        }
    }

    js(IR) {
        browser()
    }

    linuxX64 {
        binaries {
            executable {
                entryPoint = "com.github.shalva97.main"
                baseName = "google-auth-decode-$version-linuxX64"
            }
        }
    }

    sourceSets {
        val commonMain by getting
    }

    cocoapods {
        // Required properties
        // Specify the required Pod version here. Otherwise, the Gradle project version is used.
        version = "1.0"
        summary = "Decode Google Authenticator's QR code message"
        homepage = "https://github.com/shalva97/google-auth-decode"

        // Optional properties
        // Configure the Pod name here instead of changing the Gradle project name
        name = "google-auth-decode-CocoaPod"

        framework {
            // Required properties
            // Framework name configuration. Use this property instead of deprecated 'frameworkName'
            baseName = "google-auth-decode"

            // Optional properties
            // Dynamic framework support
            isStatic = false
            // Dependency export
            export(rootProject)
            transitiveExport = false // This is default.
            // Bitcode embedding
            embedBitcode(BITCODE)
        }

        // Maps custom Xcode configuration to NativeBuildType
        xcodeConfigurationToNativeBuildType["CUSTOM_DEBUG"] = DEBUG
        xcodeConfigurationToNativeBuildType["CUSTOM_RELEASE"] = RELEASE
    }
}

tasks.withType<ShadowJar> {
    manifest {
        attributes("Main-Class" to "com.github.shalva97.MainKt")
    }
    archiveClassifier.set("all")
    val main by kotlin.jvm().compilations
    from(main.output)
    configurations += main.compileDependencyFiles
    configurations += main.runtimeDependencyFiles
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/shalva97/google-auth-decode")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    commonMainImplementation("org.jetbrains.kotlinx:kotlinx-serialization-protobuf:1.4.1")
    commonMainImplementation("io.matthewnelson.kotlin-components:encoding-base64:1.1.3")
    commonMainImplementation("io.matthewnelson.kotlin-components:encoding-base32:1.1.3")
    commonMainImplementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.5")
    commonMainImplementation("com.eygraber:uri-kmp:0.0.9")
    commonTestImplementation(kotlin("test"))
}

rootProject.extensions.configure<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension> {
    versions.webpackCli.version = "4.10.0"
}