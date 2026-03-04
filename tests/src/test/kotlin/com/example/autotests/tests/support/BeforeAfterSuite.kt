package com.example.autotests.tests.support

import com.codeborne.selenide.Selenide
import com.example.autotests.database.DatabaseModule
import com.example.autotests.framework.SelenideConfig
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeSuite

open class BeforeAfterSuite {
    @BeforeSuite(alwaysRun = true)
    fun beforeSuite() {
        DatabaseModule.initForTests()
        SelenideConfig.configure()
    }

    @AfterMethod(alwaysRun = true)
    fun tearDown() {
        Selenide.closeWebDriver()
    }
}