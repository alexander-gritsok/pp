plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.codeborne:selenide:7.5.1")
    implementation("org.seleniumhq.selenium:selenium-java:4.23.0")
    implementation("com.google.inject:guice:7.0.0")
    implementation("org.apache.logging.log4j:log4j-api:2.24.2")
    implementation("org.apache.logging.log4j:log4j-core:2.24.2")
    implementation(project(":database"))
    testImplementation("org.testng:testng:7.10.2")
}