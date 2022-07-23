plugins {
    kotlin("multiplatform") version "1.7.0"
    kotlin("plugin.serialization") version "1.7.10"
}

group = "com.github.shalva97"
version = "0.0.11"

kotlin {
    jvm()
    mingwX64()

    macosX64("native").apply {
        binaries {
            executable {
                entryPoint = "main"
            }
        }
    }

    sourceSets {
        val commonMain by getting
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
    }
}

repositories {
    mavenCentral()
}

dependencies {
    commonMainImplementation("org.jetbrains.kotlinx:kotlinx-serialization-protobuf:1.3.3")
    commonMainImplementation("com.eygraber:uri-kmp:0.0.4")
    commonMainImplementation("io.matthewnelson.kotlin-components:encoding-base64:1.1.2")
    commonMainImplementation("io.matthewnelson.kotlin-components:encoding-base32:1.1.2")
    commonMainImplementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.5")
    commonTestImplementation(kotlin("test"))
}
