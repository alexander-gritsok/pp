package com.example.autotests.tests.support

import org.testng.ITestContext
import org.testng.annotations.DataProvider
import java.nio.charset.StandardCharsets
import kotlin.script.experimental.api.ResultValue
import kotlin.script.experimental.api.ResultWithDiagnostics
import kotlin.script.experimental.api.ScriptCompilationConfiguration
import kotlin.script.experimental.api.ScriptEvaluationConfiguration
import kotlin.script.experimental.host.StringScriptSource
import kotlin.script.experimental.jvmhost.BasicJvmScriptingHost

object KtsDataProvider {
    private val host = BasicJvmScriptingHost()

    @JvmStatic
    @DataProvider(name = "kts")
    fun provideData(method: java.lang.reflect.Method, @Suppress("UNUSED_PARAMETER") ctx: ITestContext): Array<Array<Any>> {
        val ann = method.getAnnotation(KtsSource::class.java)
            ?: error("Missing @KtsSource on ${method.name}")

        val resource = Thread.currentThread().contextClassLoader.getResource(ann.filepath)
            ?: error("Resource not found on classpath: ${ann.filepath}")
        val script = resource.openStream().use { it.readBytes().toString(StandardCharsets.UTF_8) }

        val result = host.eval(
            StringScriptSource(script),
            ScriptCompilationConfiguration(),
            ScriptEvaluationConfiguration()
        )

        val map: Map<String, Any?> = when (result) {
            is ResultWithDiagnostics.Success -> {
                when (val rv = result.value.returnValue) {
                    is ResultValue.Value -> {
                        val value = rv.value
                        @Suppress("UNCHECKED_CAST")
                        if (value is Map<*, *>) value as Map<String, Any?>
                        else error("KTS must return Map<String, Any?>, got: ${value?.javaClass?.name}")
                    }
                    is ResultValue.Unit -> emptyMap()
                    is ResultValue.Error -> error("KTS evaluation returned error: ${rv.error}")
                    else -> error("Unexpected script return type: ${rv::class.qualifiedName}")
                }
            }
            is ResultWithDiagnostics.Failure -> error("Failed to evaluate KTS: ${result.reports.joinToString { it.message }}")
        }

        return arrayOf(arrayOf(map))
    }
}