package com.amalwin.kycountryandflag.views

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amalwin.kycountryandflag.databinding.ActivityMainBinding
import com.amalwin.kycountryandflag.viewmodels.ListViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var countryViewModel: ListViewModel
    private lateinit var mainActivityBinding: ActivityMainBinding

    private val countriesListAdapter: CountriesListAdapter =
        CountriesListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
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
        observeCountriesList()
    }

    private fun observeCountriesList() {
        countryViewModel.countriesListLiveData.observe(this, Observer {
            countriesListAdapter.updateCountriesList(it)

        })

        countryViewModel.isCountriesLoadingErrorLiveData.observe(this, Observer {
            mainActivityBinding.loadingErrorMessage.visibility = if (it) View.VISIBLE else View.GONE
            mainActivityBinding.progressBar.visibility = View.GONE
        })

        countryViewModel.isLoadingLiveData.observe(this, Observer {
            mainActivityBinding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })
    }
}