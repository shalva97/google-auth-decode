import com.google.protobuf.gradle.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
    id("com.google.protobuf") version "0.8.17"
    kotlin("kapt") version "1.3.70"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    `maven-publish`
}

group = "com.github.shalva97"
version = "0.0.10"

val jar by tasks.getting(Jar::class) {
    manifest {
        attributes["Main-Class"] = "GoogleAuthDecoderKt"
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.protobuf:protobuf-javalite:3.21.1")
    implementation("commons-codec:commons-codec:1.15")

    implementation("info.picocli:picocli:4.6.3")
    kapt("info.picocli:picocli-codegen:4.6.3")

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

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["kotlin"])
        }
    }
}

kapt {
    arguments {
        arg("project", "${project.group}/${project.name}")
    }
}