package com.sky.recapfinalproject.utility

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sky.recapfinalproject.R

fun ImageView.downloadFromInternet(url: String?, placeHolder:CircularProgressDrawable){
    val options = RequestOptions.placeholderOf(placeHolder).error(R.mipmap.ic_launcher_round)
    Glide.with(context).setDefaultRequestOptions(options).load(url).into(this)
}

fun createPlaceHolder(context: Context) : CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth=8F
        centerRadius=40F
        start()
    }
}