package com.example.autotests.ui.pages

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.Selenide.open
import com.codeborne.selenide.SelenideElement
import com.google.inject.Inject

class GoogleHomePage @Inject constructor() {
    private val searchButton: SelenideElement = `$`(\"input[name='btnK']\")

    fun openUrl(url: String): GoogleHomePage {
        open(url)
        return this
    }

    fun assertSearchButtonPresent(): GoogleHomePage {
        searchButton.should(Condition.exist)
        return this
    }
}