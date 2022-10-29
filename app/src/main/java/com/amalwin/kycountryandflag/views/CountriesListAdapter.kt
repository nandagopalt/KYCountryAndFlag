package com.amalwin.kycountryandflag.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amalwin.kycountryandflag.R
import com.amalwin.kycountryandflag.databinding.CountryLayoutBinding
import com.amalwin.kycountryandflag.models.Country
import com.amalwin.kycountryandflag.utils.getCircularProgressDrawable
import com.amalwin.kycountryandflag.utils.setImage

class CountriesListAdapter(private val countriesList: ArrayList<Country>) :
    RecyclerView.Adapter<CountriesListAdapter.CountriesListViewHolder>() {

    private lateinit var countryLayoutBinding: CountryLayoutBinding

    fun updateCountriesList(countriesList: List<Country>) {
        this.countriesList.clear()
        this.countriesList.addAll(countriesList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesListViewHolder {
        countryLayoutBinding =
            CountryLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        /*val view =
            LayoutInflater.from(parent.context).inflate(R.layout.country_layout, parent, false)*/
        return CountriesListViewHolder(countryLayoutBinding)
    }

    override fun onBindViewHolder(holder: CountriesListViewHolder, position: Int) {
        val country = countriesList[position]
        holder.bind(country)
    }

    override fun getItemCount(): Int {
        return countriesList.size
    }

    class CountriesListViewHolder(countryItemBinding: CountryLayoutBinding) :
        RecyclerView.ViewHolder(countryItemBinding.root) {
        private val countryName = countryItemBinding.countryName
        private val capitalName = countryItemBinding.capitalName
        private val flagImage = countryItemBinding.countryFlagImageView
        private val view = countryItemBinding.root
        private val progressDrawable = getCircularProgressDrawable(view.context)
        fun bind(country: Country) {
            countryName.text = country.countryName
            capitalName.text = country.capitalName
            flagImage.setImage(country.countryFlag, progressDrawable)

        }
    }
}