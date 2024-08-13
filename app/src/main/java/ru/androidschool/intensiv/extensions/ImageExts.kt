package ru.androidschool.intensiv.extensions

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadImageByUrl(url: String) {
     Picasso.get()
         .load(url)
         .into(this)
 }