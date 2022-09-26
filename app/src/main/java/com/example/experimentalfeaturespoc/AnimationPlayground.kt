package com.example.experimentalfeaturespoc

import android.animation.ObjectAnimator
import android.graphics.Color
import android.icu.number.Scale
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.core.content.ContextCompat
import com.example.experimentalfeaturespoc.databinding.ActivityAnimationPlaygroundBinding

class AnimationPlayground : AppCompatActivity() {

    private lateinit var animationPlaygroundActivityBinding: ActivityAnimationPlaygroundBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        animationPlaygroundActivityBinding =
            ActivityAnimationPlaygroundBinding.inflate(layoutInflater)
        setContentView(animationPlaygroundActivityBinding.root)

        with(animationPlaygroundActivityBinding) {
            btnAnimate.setOnClickListener {
//                bgCircle.backgroundTintList = getColorStateList(Color.parseColor("#E56303"))
                ivGhee.animate()
                val scaleAnimation = ScaleAnimation(1f,
                    1.6f,
                    1f,
                    1.6f,
                    Animation.RELATIVE_TO_SELF,
                    0.45f,
                    Animation.RELATIVE_TO_SELF,
                    1f).apply {
                    fillAfter = true
                    duration = 500
                }
                ivGhee.startAnimation(scaleAnimation)
            }

            btnDeAnimate.setOnClickListener {
//                bgCircle.backgroundTintList = getColorStateList(Color.parseColor("#FFF4EC"))
                val scaleAnimation = ScaleAnimation(1.6f,
                    1f,
                    1.6f,
                    1f,
                    Animation.RELATIVE_TO_SELF,
                    0.45f,
                    Animation.RELATIVE_TO_SELF,
                    1f).apply {
                    fillAfter = true
                    duration = 500
                }
                ivGhee.startAnimation(scaleAnimation)
            }
        }

    }


}