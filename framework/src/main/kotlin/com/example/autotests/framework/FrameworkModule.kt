package com.example.autotests.framework

import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.google.inject.Singleton
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class FrameworkModule : AbstractModule() {
    @Provides
    @Singleton
    fun provideLogger(): Logger = LogManager.getLogger("Tests")
}