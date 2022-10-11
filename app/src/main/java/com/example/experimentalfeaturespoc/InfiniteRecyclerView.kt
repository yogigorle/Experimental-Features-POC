package com.example.experimentalfeaturespoc

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
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

class DrawableEdgeEffectFactory() : RecyclerView.EdgeEffectFactory() {

    private var paint: Paint = Paint()

    init {
        paint.apply {
            color = R.color.purple_200
            style = Paint.Style.FILL
        }
    }

    override fun createEdgeEffect(rv: RecyclerView, direction: Int): EdgeEffect {
        return object : EdgeEffect(rv.context) {
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

                canvas?.drawRect(30f, 30f, 80f, 80f, paint)

                return super.draw(canvas)

            }

        }
    }
}
