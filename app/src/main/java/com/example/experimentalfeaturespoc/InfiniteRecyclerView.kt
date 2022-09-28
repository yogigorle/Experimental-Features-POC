package com.example.experimentalfeaturespoc

import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EdgeEffect
import androidx.recyclerview.widget.RecyclerView
import com.example.experimentalfeaturespoc.databinding.ActivityInfiniteRecyclerViewBinding
import timber.log.Timber

class InfiniteRecyclerView : AppCompatActivity() {

    private lateinit var infiniteRecyclerViewBinding: ActivityInfiniteRecyclerViewBinding
    private val itemsAdapter by lazy { ItemsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        infiniteRecyclerViewBinding = ActivityInfiniteRecyclerViewBinding.inflate(layoutInflater)
        setContentView(infiniteRecyclerViewBinding.root)

        val list = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        with(infiniteRecyclerViewBinding) {
            itemsAdapter.submitList(list)
            rvInfiniteScroll.adapter = itemsAdapter
            rvInfiniteScroll.edgeEffectFactory = DrawableEdgeEffectFactory()
        }
    }
}

class DrawableEdgeEffectFactory(): RecyclerView.EdgeEffectFactory(){
    override fun createEdgeEffect(view: RecyclerView, direction: Int): EdgeEffect {
        return object : EdgeEffect(view.context){
            override fun onPull(deltaDistance: Float) {
                super.onPull(deltaDistance)
                Timber.e("ON PULLED, $deltaDistance")
            }

            override fun onPull(deltaDistance: Float, displacement: Float) {
                super.onPull(deltaDistance, displacement)
                Timber.e("ON PULLED, $deltaDistance")
            }

            override fun onPullDistance(deltaDistance: Float, displacement: Float): Float {
                return super.onPullDistance(deltaDistance, displacement)

            }

            override fun onRelease() {
                super.onRelease()
                Timber.e("ON RELEASED")
            }


            override fun draw(canvas: Canvas?): Boolean {
                return super.draw(canvas)
            }

        }
    }
}
