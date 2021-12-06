package com.example.experimentalfeaturespoc

import android.content.Context
import android.content.ContextWrapper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

abstract class BaseActivity : AppCompatActivity() {


    override fun attachBaseContext(newBase: Context?) {

        var selectedLocale = ""

        newBase?.let {
            lifecycleScope.launch {
                DataStoreManager.getString(Constants.localeLang).collect {
                    it?.let { localeLang ->
                        selectedLocale = localeLang
                    }
                }
            }

//            if (selectedLocale.isNotEmpty()) {
            val localeUpdatedContext = LocaleUtils.updateLocale(it, Locale(selectedLocale))
            super.attachBaseContext(localeUpdatedContext)
//            }


        }

    }
}