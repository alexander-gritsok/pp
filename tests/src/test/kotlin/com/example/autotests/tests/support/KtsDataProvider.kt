package com.example.autotests.tests.support

import org.testng.ITestContext
import org.testng.annotations.DataProvider
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
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
            ?: error(\"Missing @KtsSource on \${method.name}\")
        val path = Paths.get(\"src\", \"test\", \"resources\", ann.filepath)
        val script = Files.readString(path, StandardCharsets.UTF_8)

        val result = host.eval(
            StringScriptSource(script),
            ScriptCompilationConfiguration(),
            ScriptEvaluationConfiguration()
        )

        val map: Map<String, Any?> = when (result) {
            is ResultWithDiagnostics.Success -> {
                val rv = result.value.returnValue
                val r = rv.scriptResult
                @Suppress(\"UNCHECKED_CAST\")
                when (r) {
                    is Map<*, *> -> r as Map<String, Any?>
                    else -> error(\"KTS must return Map<String, Any?>, got: \${r?.javaClass?.name}\")
                }
            }
            is ResultWithDiagnostics.Failure -> error(\"Failed to evaluate KTS: \${result.reports.joinToString { it.message }}\")
        }

        return arrayOf(arrayOf(map))
    }
}