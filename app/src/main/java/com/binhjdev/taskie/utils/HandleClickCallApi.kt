package com.binhjdev.taskie.utils

import android.view.View

class HandleClickCallApi {
    fun View.setThrottleClickListener(throttle: Long = 200L, onClicked: (View?) -> Unit) {
        this.setOnClickListener { v ->
            v.isClickable = false
            v.postDelayed({
                v.isClickable = true
            }, throttle)
            onClicked(v)
        }
    }
}