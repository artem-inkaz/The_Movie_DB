package ru.androidschool.intensiv.extensions

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.imageExt(url: String) {
     Picasso.get()
         .load(url)
         .into(this)
 }