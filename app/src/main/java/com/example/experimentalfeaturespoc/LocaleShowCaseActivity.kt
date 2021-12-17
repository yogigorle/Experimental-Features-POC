package com.example.experimentalfeaturespoc

import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import com.example.experimentalfeaturespoc.databinding.ActivityLocaleShowCaseBinding
import kotlinx.coroutines.launch
import org.w3c.dom.Text
import java.lang.StringBuilder
import java.util.*

class LocaleShowCaseActivity : BaseActivity() {

    private lateinit var localeShowCaseBinding: ActivityLocaleShowCaseBinding

    private var text = ""
    private var delete = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        localeShowCaseBinding = ActivityLocaleShowCaseBinding.inflate(layoutInflater)
        setContentView(localeShowCaseBinding.root)

        val uniqueId = UUID.randomUUID().toString()
        Log.e("uniqueId", uniqueId)


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

            etInput.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                    text = etInput.text.toString()
                    //handle delete case
                    if (count > after) {
                        delete = true
                    }


                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                    //will take a string builder
                    s?.let {
                        val sb = StringBuilder(it)
                        val replacePos = etInput.selectionEnd

                        if (s.length != 6) {
                            //handle delete or replace case
                            if (delete) {
                                sb.insert(replacePos, "-")
                            } else {
                                if (replacePos < s.length)
                                    sb.deleteCharAt(replacePos)
                            }
                            if(replacePos < s.length || delete){
                                etInput.setText(sb.toString())
                                etInput.setSelection(replacePos)
                            }else{
                                etInput.setText(text)
                                etInput.setSelection(replacePos - 1)
                            }

                        }
                    }
                    delete = false

                }

                override fun afterTextChanged(s: Editable?) {

                }

            })


        }


    }


}