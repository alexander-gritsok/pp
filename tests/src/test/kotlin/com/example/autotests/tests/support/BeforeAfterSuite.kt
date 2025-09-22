package com.example.autotests.tests.support

import com.codeborne.selenide.WebDriverRunner
import com.example.autotests.framework.SelenideConfig
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeSuite

open class BeforeAfterSuite {
    @BeforeSuite(alwaysRun = true)
    fun beforeSuite() {
        SelenideConfig.configure()
    }

    @AfterMethod(alwaysRun = true)
    fun tearDown() {
        WebDriverRunner.closeWebDriver()
    }
}