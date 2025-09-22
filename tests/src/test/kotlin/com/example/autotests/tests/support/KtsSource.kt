package com.example.autotests.tests.support

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class KtsSource(val filepath: String)