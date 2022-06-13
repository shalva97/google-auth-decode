import com.google.protobuf.gradle.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
    id("com.google.protobuf") version "0.8.17"
    `maven-publish`
}

group = "com.github.shalva97"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.protobuf:protobuf-javalite:3.21.1")
    implementation("commons-codec:commons-codec:1.15")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.19.4"
    }

    generateProtoTasks {
        ofSourceSet("main").forEach { task ->
            task.builtins {
                getByName("java") {
                    option("lite")
                }
            }
        }
    }
}
