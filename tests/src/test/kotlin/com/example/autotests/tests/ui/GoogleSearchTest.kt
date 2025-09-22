package com.example.autotests.tests.ui

import com.example.autotests.tests.support.*
import com.example.autotests.ui.pages.GoogleHomePage
import com.google.inject.Inject
import org.testng.annotations.Guice
import org.testng.annotations.Test

@Guice(moduleFactory = GuiceModuleFactory::class)
class GoogleSearchTest : BeforeAfterSuite() {

    @Inject
    lateinit var page: GoogleHomePage

    @Test(dataProvider = "kts", dataProviderClass = KtsDataProvider::class)
    @KtsSource(filepath = "test-data/googleTestData.kts")
    fun googleHasSearchButton(data: Map<String, Any?>) {
        val url = data["url"] as String
        page.openUrl(url).assertSearchButtonPresent()
    }
}