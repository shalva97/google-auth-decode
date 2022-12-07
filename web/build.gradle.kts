plugins {
    kotlin("js")
    id("org.jetbrains.compose") version "1.2.1"
}

group = "com.github.shalva97"
version = findProperty("version") as String

kotlin {
    js(IR) {
        browser()
        binaries.executable()
    }
    sourceSets {
        val main by getting
    }
}

repositories {
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven") }
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-html:0.8.1")

    implementation(kotlin("stdlib-js"))

    implementation(compose.web.core)
    implementation(compose.runtime)
    implementation(project(":shared"))
}
