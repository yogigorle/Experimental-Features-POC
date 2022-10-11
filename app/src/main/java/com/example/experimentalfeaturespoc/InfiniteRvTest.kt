package com.example.experimentalfeaturespoc

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.ListAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.experimentalfeaturespoc.databinding.ActivityInfiniteRvTestBinding
import com.example.experimentalfeaturespoc.databinding.CustomTabItemBinding
import com.facebook.internal.CustomTab
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.Tab

class InfiniteRvTest : AppCompatActivity() {
    private lateinit var infiniteRvTestBinding: ActivityInfiniteRvTestBinding
    private val sampleDataAdapter by lazy { SampleDataAdpater() }
    private val categoriesList = arrayListOf<Int>()
    private var selectedPos = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        infiniteRvTestBinding = ActivityInfiniteRvTestBinding.inflate(layoutInflater)
        setContentView(infiniteRvTestBinding.root)
        with(infiniteRvTestBinding) {
            categoriesList.addAll(
                arrayListOf(
                    1,
                    2
                )
            )

            categoriesList.forEachIndexed { index, ints ->

                tlMonthlyPacks.addTab(
                    tlMonthlyPacks.newTab()
                )
            }

            tlMonthlyPacks.setSelectedTabIndicator(R.drawable.ic_chevron_left)




        }
    }

    inner class MonthlyPacksViewPagerAdapter(
        supportFragmentManager: FragmentManager,
        lifecycle: Lifecycle
    ) : FragmentStateAdapter(supportFragmentManager, lifecycle) {
        override fun getItemCount() = categoriesList.size

        override fun createFragment(position: Int) =
            MonthlyPackFragment.newInstance(categoriesList[position])

    }
}
