import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.7.10"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("java")
    id("com.adarshr.test-logger") version "3.2.0"
//    id("co.touchlab.faktory.kmmbridge") version "0.3.2"
    `maven-publish`
}

//kmmbridge {
//    githubReleaseArtifacts()
//    githubReleaseVersions()
//    versionPrefix.set("0.1")
//    spm()
//}

kotlin {
    jvm {
        val main by compilations.getting {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_11.toString()
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

    val xcf = XCFramework()

    macosX64() {
        binaries {
            executable {
                entryPoint = "com.github.shalva97.main"
                baseName = "google-auth-decode-$version-macosX64"
            }
            framework {
                xcf.add(this)
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
        val macosX64Main by getting {
            dependsOn(commonMain)
        }
    }
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
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
    commonMainImplementation("com.eygraber:uri-kmp:0.0.16")
    commonTestImplementation(kotlin("test"))
}

rootProject.extensions.configure<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension> {
    versions.webpackCli.version = "4.10.0"
}