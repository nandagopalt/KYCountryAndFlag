package com.amalwin.kycountryandflag.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name")
    @Expose
    val countryName: String?,
    @SerializedName("capital")
    @Expose
    val capitalName: String?,
    @SerializedName("flagPNG")
    @Expose
    val countryFlag: String?
)
