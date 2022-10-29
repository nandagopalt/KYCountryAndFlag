package com.amalwin.kycountryandflag.utils

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.amalwin.kycountryandflag.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun getCircularProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 25f
        start()
    }
}

fun ImageView.setImage(uri: String?, progressDrawable: CircularProgressDrawable) {
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)
    Glide.with(this.context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)
}