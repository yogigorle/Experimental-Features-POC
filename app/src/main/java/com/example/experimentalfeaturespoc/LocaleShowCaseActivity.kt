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
import java.util.*

class LocaleShowCaseActivity : BaseActivity() {

    private lateinit var localeShowCaseBinding: ActivityLocaleShowCaseBinding

    private var enteredWordsList = MutableList(5) { "-" }

    var index = 0


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

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

                override fun afterTextChanged(s: Editable?) {
                    s?.let {
                        if(index < 5){
                            enteredWordsList[index++] = ""
                            handleDashedEditText(enteredWordsList)
                        }else{
                            handleDashedEditText(enteredWordsList)
                        }

                    }
                }

            })


        }

        handleDashedEditText(enteredWordsList)


    }

    private fun handleDashedEditText(wordsList: MutableList<String>) {
        localeShowCaseBinding.llDashed.removeAllViews()
        for (i in wordsList.withIndex()) {
            Log.e("values", enteredWordsList.toString())
            val textView = TextView(this).apply {
                setTextAppearance(R.style.TextAppearance_MaterialComponents_Subtitle1)
                setTextColor(Color.BLACK)
            }
            val params = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            );
            textView.text = wordsList[i.index]
            params.setMargins(0, 0, 20, 0);
            textView.layoutParams = params
            localeShowCaseBinding.llDashed.addView(textView)
        }
    }


}