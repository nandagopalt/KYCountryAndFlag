package com.amalwin.kycountryandflag.models

import com.amalwin.kycountryandflag.models.Country
import io.reactivex.Single
import retrofit2.http.GET

interface CountriesAPI {
    @GET("DevTides/countries/master/countriesV2.json")
    fun getAllCountries(): Single<List<Country>>
}