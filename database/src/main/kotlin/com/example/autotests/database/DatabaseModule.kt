package com.example.autotests.database

import com.example.autotests.database.entities.DBAddress
import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.google.inject.Singleton
import io.ebean.Database
import io.ebean.DatabaseBuilder
import io.ebean.DatabaseFactory
import io.ebean.config.DatabaseConfig
import io.ebean.config.DatabaseConfigProvider
import io.ebean.datasource.DataSourceConfig

class DatabaseModule : DatabaseConfigProvider {
    //    @Provides
//    @Singleton
//    fun provideDatabase(): Database {
//        val dataSourceConfig = DataSourceConfig()
//        dataSourceConfig.setUsername("sa")
//        dataSourceConfig.setPassword("")
//        dataSourceConfig.setUrl("jdbc:h2:mem:myapp;")
//
//        val config = DatabaseConfig()
//        config.name("default")
//        config.setDataSourceConfig(dataSourceConfig)
//        config.ddlGenerate(false)
//        config.ddlRun(false)
//        config.addClass(DBAddress::class.java)
//
//        return DatabaseFactory.create(config)
//    }
    override fun apply(databaseBuilder: DatabaseBuilder?) {
        val dataSourceConfig = databaseBuilder?.settings()?.dataSourceConfig

        // Prefer system properties (optionally populated by FrameworkModule) and fall back to sensible test defaults
        val url = System.getProperty("db.url") ?: "jdbc:postgresql://localhost:5432/develop"
        val user = System.getProperty("db.user") ?: "postgres"
        val password = System.getProperty("db.password") ?: "postgres"

        dataSourceConfig?.url = url
        dataSourceConfig?.username = user
        dataSourceConfig?.password = password

        // Ensure stable defaults
        dataSourceConfig?.heartbeatFreqSecs = 0 /* disable heartbeat service */

        // Register entities
        databaseBuilder?.addClass(DBAddress::class.java)
    }
}