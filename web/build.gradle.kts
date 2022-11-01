plugins {
    kotlin("js")
    id("org.jetbrains.compose") version "1.2.0-alpha01-dev750"
}

group = "com.github.shalva97"
version = "0.0.15"

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
    implementation("org.jetbrains.kotlinx:kotlinx-html:0.8.0")

    implementation(kotlin("stdlib-js"))

    implementation(compose.web.core)
    implementation(compose.runtime)
    implementation(rootProject)

}
