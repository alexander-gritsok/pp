package com.example.autotests.framework

import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.google.inject.Singleton
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.util.Properties
import java.io.InputStream

class FrameworkModule : AbstractModule() {
    override fun configure() {
        // Load config.properties from classpath (if present)
        val props = Properties()
        val resourceName = "config.properties"
        val stream: InputStream? = Thread.currentThread().contextClassLoader.getResourceAsStream(resourceName)
        if (stream != null) {
            stream.use { props.load(it) }
        }

        // Prime APP_DB_* from properties if not already set as system properties
        props.getProperty("APP_DB_HOST_URL")?.let { if (System.getProperty("APP_DB_HOST_URL") == null) System.setProperty("APP_DB_HOST_URL", it) }
        props.getProperty("APP_DB_USER_NAME")?.let { if (System.getProperty("APP_DB_USER_NAME") == null) System.setProperty("APP_DB_USER_NAME", it) }
        props.getProperty("APP_DB_USER_PSWD")?.let { if (System.getProperty("APP_DB_USER_PSWD") == null) System.setProperty("APP_DB_USER_PSWD", it) }
        props.getProperty("SUPERADMIN_DB_NAME")?.let { if (System.getProperty("SUPERADMIN_DB_NAME") == null) System.setProperty("SUPERADMIN_DB_NAME", it) }

        // Map APP_DB_* environment variables (or system props) to database module expected keys
        val env = System.getenv()
        val appDbUrl = System.getProperty("APP_DB_HOST_URL", env["APP_DB_HOST_URL"])
        val appDbUser = System.getProperty("APP_DB_USER_NAME", env["APP_DB_USER_NAME"])
        val appDbPwd = System.getProperty("APP_DB_USER_PSWD", env["APP_DB_USER_PSWD"])
        val superAdminDb = System.getProperty("SUPERADMIN_DB_NAME", env["SUPERADMIN_DB_NAME"])

        // Compose full JDBC URL if SUPERADMIN_DB_NAME provided and base URL ends with slash
        val effectiveUrl = when {
            appDbUrl != null && superAdminDb != null && appDbUrl.endsWith("/") -> appDbUrl + superAdminDb
            else -> appDbUrl
        }

        // Set properties only if not already explicitly set
        if (effectiveUrl != null && System.getProperty("db.url") == null) {
            System.setProperty("db.url", effectiveUrl)
        }
        if (appDbUser != null && System.getProperty("db.user") == null) {
            System.setProperty("db.user", appDbUser)
        }
        if (appDbPwd != null && System.getProperty("db.password") == null) {
            System.setProperty("db.password", appDbPwd)
        }
    }
    @Provides
    @Singleton
    fun provideLogger(): Logger = LogManager.getLogger("Tests")
}