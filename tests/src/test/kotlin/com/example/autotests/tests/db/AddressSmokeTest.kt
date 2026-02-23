package com.example.autotests.tests.db

import com.example.autotests.database.entities.DBAddress
import com.example.autotests.tests.support.BeforeAfterSuite
import io.ebean.DB
import org.apache.logging.log4j.LogManager
import org.testng.Assert
import org.testng.annotations.Test

class AddressSmokeTest : BeforeAfterSuite() {

    @Test
    fun firstAddress_hasNonBlankName_andIsLogged() {
        val logger = LogManager.getLogger(AddressSmokeTest::class.java)
        val first = DB.find(DBAddress::class.java)
            .findList()
        logger.info("List size: ${first.size}")

        if (first.isEmpty()) {
            Assert.fail("Expected at least one record in addresses table")
            return
        }

        val name = first.first().address1
        Assert.assertTrue(name.isNotBlank(), "addressName must not be null or blank")

        logger.info("First addressName: {}", name)
        println("First addressName: $name")
    }
}


