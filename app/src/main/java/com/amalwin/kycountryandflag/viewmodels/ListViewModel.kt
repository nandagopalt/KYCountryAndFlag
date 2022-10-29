package com.amalwin.kycountryandflag.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amalwin.kycountryandflag.di.DaggerCountryAPIComponent
import com.amalwin.kycountryandflag.models.Country
import com.amalwin.kycountryandflag.models.CountryService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class ListViewModel : ViewModel() {
    val countriesListLiveData = MutableLiveData<List<Country>>()
    val isCountriesLoadingErrorLiveData = MutableLiveData<Boolean>()
    val isLoadingLiveData = MutableLiveData<Boolean>()

    @Inject
    lateinit var countryService: CountryService
    private val disposable = CompositeDisposable()

    init {
        DaggerCountryAPIComponent.create().inject(this)
    }

    fun refresh() {
        fetchCountries();
    }

    private fun fetchCountries() {
        isCountriesLoadingErrorLiveData.value = false
        isLoadingLiveData.value = true
        disposable.add(
            countryService.getAllCountries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>() {
                    /**
                     * Notifies the [SingleObserver] with a single item and that the [Single] has finished sending
                     * push-based notifications.
                     *
                     *
                     * The `Single` will not call this method if it calls [.onError].
                     *
                     * @param countryList
                     * the item emitted by the `Single`
                     */
                    override fun onSuccess(countryList: List<Country>) {
                        countriesListLiveData.value = countryList
                        isLoadingLiveData.value = false
                        isCountriesLoadingErrorLiveData.value = false
                    }

                    /**
                     * Notifies the [SingleObserver] that the [Single] has experienced an error condition.
                     *
                     *
                     * If the `Single` calls this method, it will not thereafter call [.onSuccess].
                     *
                     * @param e
                     * the exception encountered by the `Single`
                     */
                    override fun onError(e: Throwable) {
                        isCountriesLoadingErrorLiveData.value = true
                        isLoadingLiveData.value = false
                        Log.e("Error", e.cause.toString())
                    }
                })
        )
    }

    /**
     * This method will be called when this ViewModel is no longer used and will be destroyed.
     *
     *
     * It is useful when ViewModel observes some data and you need to clear this subscription to
     * prevent a leak of this ViewModel.
     */
    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}