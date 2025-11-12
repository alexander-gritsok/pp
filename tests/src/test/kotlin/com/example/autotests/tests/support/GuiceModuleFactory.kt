package com.example.autotests.tests.support

import com.example.autotests.database.DatabaseModule
import com.example.autotests.framework.FrameworkModule
import com.google.inject.Module
import org.testng.IModuleFactory
import org.testng.ITestContext

class GuiceModuleFactory : IModuleFactory {
    override fun createModule(context: ITestContext, testClass: Class<*>): Module {
        return com.google.inject.util.Modules.combine(
            FrameworkModule()
        )
    }
}