package com.example.experimentalfeaturespoc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import com.example.experimentalfeaturespoc.databinding.ActivityTabLayoutChipsSelectionBinding
import com.google.android.material.tabs.TabLayoutMediator


class TabLayoutChipsSelection : AppCompatActivity() {

    private lateinit var tabLayoutBinding: ActivityTabLayoutChipsSelectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tabLayoutBinding = ActivityTabLayoutChipsSelectionBinding.inflate(layoutInflater)
        setContentView(tabLayoutBinding.root)

        with(tabLayoutBinding) {

            val list = arrayOf(1, 2, 3)
            val sampleAdapter = SampleAdapter(list)
            vpData.adapter = sampleAdapter

            TabLayoutMediator(tabLayout, vpData) { tab, pos ->
                tab.text = list[pos].toString()
            }.attach()

            for (i in 0 until tabLayout.tabCount) {
                val tab = (tabLayout.getChildAt(0) as ViewGroup).getChildAt(i)
                val p = tab.layoutParams as ViewGroup.MarginLayoutParams
                p.setMargins(dpToPx(8), 0, dpToPx(8), 0)
                tab.requestLayout()
            }
        }
    }


}