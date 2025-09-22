package com.example.autotests.database

import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.google.inject.Singleton
import com.zaxxer.hikari.HikariDataSource
import io.ebean.Database
import io.ebean.DatabaseFactory
import io.ebean.config.DatabaseConfig

class DatabaseModule : AbstractModule() {
    @Provides
    @Singleton
    fun provideDatabase(): Database {
        val dataSource = HikariDataSource().apply {
            jdbcUrl = System.getProperty("db.url", "jdbc:h2:mem:tests;DB_CLOSE_DELAY=-1")
            username = System.getProperty("db.user", "sa")
            password = System.getProperty("db.password", "")
        }
        val config = DatabaseConfig().apply {
            name = "default"
            this.dataSource = dataSource
            isDdlGenerate = false
            isDdlRun = false
        }
        return DatabaseFactory.create(config)
    }
}