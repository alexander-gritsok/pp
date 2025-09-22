package com.example.autotests.framework

import com.codeborne.selenide.Configuration
import org.openqa.selenium.remote.DesiredCapabilities
import java.nio.file.Paths
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object SelenideConfig {
    fun configure() {
        Configuration.browser = System.getProperty("browser", "chrome")
        Configuration.headless = System.getProperty("headless", "false").toBoolean()
        Configuration.timeout = System.getProperty("timeoutMs", "8000").toLong()
        Configuration.pageLoadTimeout = System.getProperty("pageLoadTimeoutMs", "30000").toLong()
        Configuration.reportsFolder = Paths.get(
            System.getProperty("reports.dir", "tests/build/reports/selenide"),
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")),
            Thread.currentThread().id.toString()
        ).toString()
        Configuration.savePageSource = true
        Configuration.screenshots = true
        Configuration.downloadsFolder = System.getProperty("downloads.dir", "tests/build/downloads")
        Configuration.fastSetValue = true
        Configuration.remote = System.getProperty("selenide.remote", null)

        val caps = DesiredCapabilities()
        if (System.getProperty("selenoid.enable", "false").toBoolean()) {
            caps.setCapability("enableVNC", true)
            if (System.getProperty("selenoid.video", "false").toBoolean()) {
                caps.setCapability("enableVideo", true)
                caps.setCapability("videoName", "test-${System.currentTimeMillis()}-${Thread.currentThread().id}.mp4")
            }
        }
        Configuration.browserCapabilities = caps
    }
}