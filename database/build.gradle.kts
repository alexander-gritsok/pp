plugins {
    kotlin("jvm")
    id("io.ebean")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.ebean:ebean:14.5.0")
    implementation("com.zaxxer:HikariCP:5.1.0")
    implementation("com.h2database:h2:2.3.232")
    implementation("org.slf4j:slf4j-api:2.0.13")
    implementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.24.2")
    implementation("com.google.inject:guice:7.0.0") // add this
}