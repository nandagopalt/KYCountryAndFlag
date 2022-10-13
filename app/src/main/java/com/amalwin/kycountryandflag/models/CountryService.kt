package com.amalwin.kycountryandflag.models


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CountryService {
    private val countriesAPI: CountriesAPI

    init {
        countriesAPI = Retrofit.Builder().baseUrl("https://raw.githubusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
            .create(CountriesAPI::class.java)
    }

    fun getAllCountries(): Single<List<Country>> = countriesAPI.getAllCountries()
}