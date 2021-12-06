package com.example.experimentalfeaturespoc

import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.os.LocaleList
import com.google.android.material.internal.ContextUtils
import java.util.*

class LocaleUtils(baseContext: Context) : ContextWrapper(baseContext) {

    companion object {
        fun updateLocale(c: Context, selectedLocale: Locale): ContextWrapper {
            var context = c
            val resources = context.resources
            val config = resources.configuration

            //handling legacy versions below noughat
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val localeList = LocaleList(selectedLocale)
                LocaleList.setDefault(localeList)
                config.setLocales(localeList)
                context = context.createConfigurationContext(config)
            } else {
                config.setLocale(selectedLocale)
                resources.updateConfiguration(config, resources.displayMetrics)
            }

            return LocaleUtils(context)


        }
    }
}