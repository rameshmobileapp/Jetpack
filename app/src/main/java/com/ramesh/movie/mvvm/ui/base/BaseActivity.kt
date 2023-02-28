package com.ramesh.movie.mvvm.ui.base

import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.ramesh.movie.R

abstract class BaseActivity : AppCompatActivity() {

    abstract fun setUpViewBinding()

    abstract fun setUpDataBinding()

    fun fadeOutAnimation(): Animation {
        return AnimationUtils.loadAnimation(applicationContext, R.anim.fade_out)
    }

    fun fadeInAnimation(): Animation {
        return AnimationUtils.loadAnimation(applicationContext, R.anim.fade_in)
    }

}