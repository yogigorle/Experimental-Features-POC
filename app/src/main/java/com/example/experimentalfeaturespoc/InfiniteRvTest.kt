package com.example.experimentalfeaturespoc

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.experimentalfeaturespoc.databinding.ActivityInfiniteRvTestBinding

class InfiniteRvTest : AppCompatActivity() {
    private lateinit var infiniteRvTestBinding: ActivityInfiniteRvTestBinding
    private val sampleDataAdapter by lazy { SampleDataAdpater() }
    private val categoriesList = arrayListOf<ArrayList<Int>>()
    private var selectedPos = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        infiniteRvTestBinding = ActivityInfiniteRvTestBinding.inflate(layoutInflater)
        setContentView(infiniteRvTestBinding.root)
        with(infiniteRvTestBinding){
               categoriesList.add(arrayListOf(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20))
               categoriesList.add(arrayListOf(20,21,22,23,24,25,26,27,28,29,30))
               categoriesList.add(arrayListOf(31,32,33,34,35,36,37,38,39,40))
            rvSample.adapter = sampleDataAdapter
            sampleDataAdapter.submitLit(categoriesList[selectedPos])

            rvSample.onFlingListener = object :RecyclerView.OnFlingListener(){
                override fun onFling(velocityX: Int, velocityY: Int): Boolean {
                    Log.e("fling","$velocityY")
                    if(velocityY < 4000 && selectedPos != categoriesList.size - 1){
                       val animation = AnimationUtils.loadAnimation(this@InfiniteRvTest,R.anim.enter_from_bottom)
                        rvSample.startAnimation(animation)
                        selectedPos += 1
                        sampleDataAdapter.submitLit(categoriesList[selectedPos])
                    }else if(velocityY < 0 && selectedPos != 0){
                        val animation = AnimationUtils.loadAnimation(this@InfiniteRvTest,R.anim.enter_from_bottom)
                        rvSample.startAnimation(animation)
                        selectedPos -= 1
                        sampleDataAdapter.submitLit(categoriesList[selectedPos])
                    }
                    return false
                }

            }
        }
    }
}