package com.jpm.util

import android.app.Activity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat

fun Activity.makeSystemStatusBarTransparent(color:Int) {

    this.let {

        if (!it.isFinishing) {
            val window = this.window
            val decorView = window.decorView
            val wic = WindowInsetsControllerCompat(window, decorView)
            wic.isAppearanceLightStatusBars = true
            window.statusBarColor = ContextCompat.getColor(this,color)
        }

    }

}
