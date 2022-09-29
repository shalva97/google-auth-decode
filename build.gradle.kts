import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("multiplatform") version "1.7.0"
    kotlin("plugin.serialization") version "1.7.20"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("java")
    id("com.adarshr.test-logger") version "3.2.0"
    `maven-publish`
}

group = "com.github.shalva97"
version = "0.0.13"

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
                baseName = "google-auth-decode-$version-mingwX64"
            }
        }
    }

    macosX64 {
        binaries {
            executable {
                entryPoint = "main"
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
                baseName = "google-auth-decode-$version-linuxX64"
            }
        }
    }

    sourceSets {
        val commonMain by getting
    }
}

tasks.withType<ShadowJar> {
    manifest {
        attributes("Main-Class" to "MainKt")
    }
    archiveClassifier.set("all")
    val main by kotlin.jvm().compilations
    from(main.output)
    configurations += main.compileDependencyFiles
    configurations += main.runtimeDependencyFiles
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["kotlin"])
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    commonMainImplementation("org.jetbrains.kotlinx:kotlinx-serialization-protobuf:1.4.0")
    commonMainImplementation("io.matthewnelson.kotlin-components:encoding-base64:1.1.3")
    commonMainImplementation("io.matthewnelson.kotlin-components:encoding-base32:1.1.3")
    commonMainImplementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.5")
    commonMainImplementation("com.eygraber:uri-kmp:0.0.9")
    commonTestImplementation(kotlin("test"))
}

rootProject.extensions.configure<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension> {
    versions.webpackCli.version = "4.10.0"
}