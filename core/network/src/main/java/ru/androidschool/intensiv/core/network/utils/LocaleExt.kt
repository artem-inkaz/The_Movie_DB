package ru.androidschool.intensiv.core.network.utils

import java.util.Calendar
import java.util.Locale

// Current date
val calendar = Calendar.getInstance()
val currentYear = calendar.get(Calendar.YEAR)

fun getLanguage() = Locale.getDefault().toLanguageTag().lowercase(Locale.getDefault())