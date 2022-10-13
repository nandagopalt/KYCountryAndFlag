package com.amalwin.kycountryandflag.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amalwin.kycountryandflag.R
import com.amalwin.kycountryandflag.models.Country

class CountriesListAdapter(private val countriesList: ArrayList<Country>) :
    RecyclerView.Adapter<CountriesListAdapter.CountriesListViewHolder>() {

    fun updateCountriesList(countriesList: List<Country>) {
        this.countriesList.clear()
        this.countriesList.addAll(countriesList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.country_layout, parent, false)
        return CountriesListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountriesListViewHolder, position: Int) {
        val country = countriesList[position]
        holder.bind(country)
    }

    override fun getItemCount(): Int {
        return countriesList.size
    }

    class CountriesListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val countryName = view.findViewById<TextView>(R.id.countryName)
        fun bind(country: Country) {
            countryName.text = country.countryName
        }
    }
}