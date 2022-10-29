package com.amalwin.kycountryandflag.views

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.amalwin.kycountryandflag.databinding.ActivityMainBinding
import com.amalwin.kycountryandflag.viewmodels.ListViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var countryViewModel: ListViewModel
    private lateinit var mainActivityBinding: ActivityMainBinding
    private val countriesListAdapter: CountriesListAdapter =
        CountriesListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)

        countryViewModel = ViewModelProvider(this)[ListViewModel::class.java]
        countryViewModel.refresh()

        mainActivityBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    LinearLayoutManager.VERTICAL
                )
            )
            adapter = countriesListAdapter
        }

        mainActivityBinding.apply {
            swipeRefreshLayout.setOnRefreshListener {
                swipeRefreshLayout.isRefreshing = false
                countryViewModel.refresh()
            }
        }

        observeCountriesList()
    }

    private fun observeCountriesList() {
        countryViewModel.countriesListLiveData.observe(this, Observer { countriesList ->
            countriesList?.let { countriesListAdapter.updateCountriesList(it) }

        })

        countryViewModel.isCountriesLoadingErrorLiveData.observe(this, Observer {
            it?.let {
                mainActivityBinding.loadingErrorMessage.visibility =
                    if (it) View.VISIBLE else View.GONE
                mainActivityBinding.progressBar.visibility = View.GONE
            }
        })

        countryViewModel.isLoadingLiveData.observe(this, Observer {
            it?.let {
                mainActivityBinding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
            }
        })
    }

    override fun onResume() {
        super.onResume()
        setDefaultNightMode(MODE_NIGHT_NO)
    }


}