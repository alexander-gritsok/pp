package com.example.autotests.database.entities
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "addresses")
class DBAddress(
        @Id
        val addressId: Int,
        var addressName: String? = null,
        val address1: String,
        var address2: String? = null,
        var address3: String? = null,
        val postcode: String,
        val cityId: Int,
        var phone1: String? = null,
        var phone2: String? = null,
        var fax: String? = null,
        var email: String? = null,
        var url: String? = null,
        var accesshours: String? = null,
        var notes: String? = null,
        var town: String? = null,
        var sicCode: String? = null,
        var siteContactTel: String? = null,
        var siteContactEmail: String? = null,
        var isoCountryId: String? = null,
        var countryRegionLevel_1: String? = null,
        var countryRegionLevel_2: String? = null
)


