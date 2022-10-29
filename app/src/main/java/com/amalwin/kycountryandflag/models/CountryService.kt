package com.amalwin.kycountryandflag.models


import com.amalwin.kycountryandflag.di.DaggerCountryAPIComponent
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class CountryService {
    @Inject
    lateinit var countriesAPI: CountriesAPI

    init {
        DaggerCountryAPIComponent.create().inject(this)
    }

    fun getAllCountries(): Single<List<Country>> = countriesAPI.getAllCountries()
}