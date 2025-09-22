plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":framework"))
    implementation("com.codeborne:selenide:7.5.1")
    implementation("com.google.inject:guice:7.0.0")
    implementation("org.apache.logging.log4j:log4j-api:2.24.2")
}