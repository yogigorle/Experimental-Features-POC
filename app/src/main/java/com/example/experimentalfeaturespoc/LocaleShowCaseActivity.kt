package com.example.experimentalfeaturespoc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.experimentalfeaturespoc.databinding.ActivityLocaleShowCaseBinding
import kotlinx.coroutines.launch
import java.util.*

class LocaleShowCaseActivity : BaseActivity() {

    private lateinit var localeShowCaseBinding: ActivityLocaleShowCaseBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        localeShowCaseBinding = ActivityLocaleShowCaseBinding.inflate(layoutInflater)
        setContentView(localeShowCaseBinding.root)

        val uniqueId = UUID.randomUUID().toString()
        Log.e("uniqueId",uniqueId)


        with(localeShowCaseBinding) {
            btnChangeLang.setOnClickListener {
                when {
                    rbEnglish.isChecked -> {
                        lifecycleScope.launch {
                            DataStoreManager.putString(Constants.localeLang, "en")
                            recreate()
                        }
                    }
                    rbHindi.isChecked -> {
                        lifecycleScope.launch {
                            DataStoreManager.putString(Constants.localeLang, "hi")
                            recreate()
                        }
                    }

                    rbTelugu.isChecked -> {
                        lifecycleScope.launch {
                            DataStoreManager.putString(Constants.localeLang, "te")
                            recreate()
                        }
                    }

                }
            }
        }
    }
}