package com.amalwin.kycountryandflag.di

import com.amalwin.kycountryandflag.models.CountriesAPI
import com.amalwin.kycountryandflag.models.CountryService
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class CountryAPIModule {
    @Provides
    fun providesCountryAPI(): CountriesAPI {
        return Retrofit.Builder().baseUrl("https://raw.githubusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
            .create(CountriesAPI::class.java)
    }

    @Provides
    fun providesCountryService(): CountryService {
        return CountryService()
    }
}