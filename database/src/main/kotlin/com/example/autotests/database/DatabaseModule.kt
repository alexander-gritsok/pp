package com.example.autotests.database

import com.example.autotests.database.entities.DBAddress
import io.ebean.DatabaseBuilder
import io.ebean.config.DatabaseConfigProvider

class DatabaseModule : DatabaseConfigProvider {

    override fun apply(databaseBuilder: DatabaseBuilder?) {
        val dataSourceConfig = databaseBuilder?.settings()?.dataSourceConfig ?: return

        dataSourceConfig.url(connectionUrl)
        dataSourceConfig.username(dbUser)
        dataSourceConfig.password(dbPassword)
        dataSourceConfig.heartbeatFreqSecs(0)

        databaseBuilder.addClass(DBAddress::class.java)
    }

    companion object {
        const val DB_NAME = "develop"
        const val PLATFORM = "postgres"
        const val DDL_MODE = "none" // none | dropCreate | migration
        const val USE_DOCKER = false

        private const val DEFAULT_HOST_URL = "jdbc:postgresql://localhost:5432/"
        private const val DEFAULT_USER = "postgres"
        private const val DEFAULT_PASSWORD = "postgres"

        /**
         * Call before tests run to configure ebean-test via system properties.
         * Must run before any DB access (e.g. in @BeforeSuite).
         */
        fun initForTests() {
            System.setProperty("ebean.test.platform", PLATFORM)
            System.setProperty("ebean.test.ddlMode", DDL_MODE)
            System.setProperty("ebean.test.useDocker", USE_DOCKER.toString())
            System.setProperty("ebean.test.dbName", DB_NAME)
            System.setProperty("ebean.test.postgres.username", dbUser)
            System.setProperty("ebean.test.postgres.password", dbPassword)
            System.setProperty("ebean.test.postgres.url", connectionUrl)
        }

        val connectionUrl: String
            get() {
                val base = System.getProperty("db.url") ?: DEFAULT_HOST_URL
                val url = if (base.trimEnd().endsWith("/")) base + DB_NAME else base
                return if (url.contains("?")) {
                    if (!url.contains("currentSchema=")) "$url&currentSchema=public" else url
                } else {
                    "$url?currentSchema=public"
                }
            }

        val dbUser: String
            get() = System.getProperty("db.user") ?: DEFAULT_USER

        val dbPassword: String
            get() = System.getProperty("db.password") ?: DEFAULT_PASSWORD
    }
}
