plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":framework"))
    implementation(project(":ui"))
    implementation(project(":database"))

    testImplementation("org.testng:testng:7.10.2")
    testImplementation("com.codeborne:selenide:7.5.1")
    testImplementation("com.google.inject:guice:7.0.0")

    // Kotlin scripting for KTS data provider
    testImplementation("org.jetbrains.kotlin:kotlin-scripting-jvm:2.0.20")
    testImplementation("org.jetbrains.kotlin:kotlin-scripting-jvm-host:2.0.20")

    testImplementation("org.apache.logging.log4j:log4j-api:2.24.2")
    testRuntimeOnly("org.apache.logging.log4j:log4j-core:2.24.2")
    testRuntimeOnly("org.apache.logging.log4j:log4j-slf4j2-impl:2.24.2")
}

tasks.test {
    useTestNG {
        suites("src/test/resources/testng.xml")
    }
    // class-level parallelism is configured in testng.xml
    maxParallelForks = (System.getenv("TEST_PARALLEL_FORKS") ?: "2").toInt()
    testLogging {
        events("FAILED", "SKIPPED")
    }
}