package com.amalwin.kycountryandflag.di

import com.amalwin.kycountryandflag.models.CountryService
import com.amalwin.kycountryandflag.viewmodels.ListViewModel
import dagger.Component

@Component(modules = [CountryAPIModule::class])
interface CountryAPIComponent {
    fun inject(countryService: CountryService)
    fun inject(viewModel: ListViewModel)
}