plugins {
    kotlin("jvm") version "2.0.20" apply false
    id("io.ebean") version "14.5.0" apply false
}

allprojects {
    group = "com.example.autotests"
    version = "1.0.0"
    repositories {
        mavenCentral()
    }
}

subprojects {
    plugins.withId("org.jetbrains.kotlin.jvm") {
        the<org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension>().jvmToolchain(17)
    }
}