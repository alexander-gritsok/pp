package com.example.autotests.tests.support

import com.codeborne.selenide.Screenshots
import org.apache.logging.log4j.LogManager
import org.testng.*

class LogAndArtifactsListener : ITestListener, ISuiteListener {
    private val log = LogManager.getLogger(LogAndArtifactsListener::class.java)

    override fun onStart(suite: ISuite) {
        log.info("Suite started: ${suite.name}")
    }

    override fun onFinish(suite: ISuite) {
        log.info("Suite finished: ${suite.name}")
    }

    override fun onTestFailure(result: ITestResult) {
        val shot = Screenshots.takeScreenShotAsFile()
        log.error("Test failed: ${result.name}. Screenshot: ${shot?.absolutePath}")
    }

    override fun onTestSkipped(result: ITestResult) {
        log.warn("Test skipped: ${result.name}")
    }

    override fun onTestSuccess(result: ITestResult) {
        log.info("Test passed: ${result.name}")
    }
}