package com.amalwin.kycountryandflag.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amalwin.kycountryandflag.models.Country

class ListViewModel : ViewModel() {
    val countriesListLiveData = MutableLiveData<List<Country>>()
    val isCountriesLoadingErrorLiveData = MutableLiveData<Boolean>()
    val isLoadingLiveData = MutableLiveData<Boolean>()

    fun refresh() {
        fetchCountries();
    }

    private fun fetchCountries() {
        val countries = listOf<Country>(
            Country("Country 1"),
            Country("Country 2"),
            Country("Country 3"),
            Country("Country 4"),
            Country("Country 5"),
            Country("Country 6"),
            Country("Country 7"),
            Country("Country 8"),
            Country("Country 9"),
            Country("Country 10"),
            Country("Counrty 11")
        )

        isCountriesLoadingErrorLiveData.value = false
        isLoadingLiveData.value = false
        countriesListLiveData.value = countries
    }
}