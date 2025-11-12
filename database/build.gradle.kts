plugins {
    id("io.ebean")
    kotlin("jvm")
    kotlin("kapt")
    `java-library`
}

dependencies {
    implementation(kotlin("stdlib"))
    api("io.ebean:ebean:17.1.0")
    implementation("org.slf4j:slf4j-api:2.0.13")
    implementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.24.2")
    implementation("com.google.inject:guice:7.0.0")
    kapt("io.ebean:kotlin-querybean-generator:17.1.0")
    testImplementation("io.ebean:ebean-test:17.1.0")
}