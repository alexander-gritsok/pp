package com.example.autotests.tests.support

import com.example.autotests.database.DatabaseModule
import com.example.autotests.framework.FrameworkModule
import com.google.inject.Guice
import com.google.inject.Injector

object GuiceInjector {
    val injector: Injector by lazy {
        Guice.createInjector(
            FrameworkModule(),
            DatabaseModule()
        )
    }
}