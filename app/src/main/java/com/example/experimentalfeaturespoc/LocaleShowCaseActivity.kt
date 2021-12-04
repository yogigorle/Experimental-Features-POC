package com.example.experimentalfeaturespoc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.experimentalfeaturespoc.databinding.ActivityLocaleShowCaseBinding

class LocaleShowCaseActivity : AppCompatActivity() {

    private lateinit var localeShowCaseBinding: ActivityLocaleShowCaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        localeShowCaseBinding = ActivityLocaleShowCaseBinding.inflate(layoutInflater)
        setContentView(localeShowCaseBinding.root)
    }
}