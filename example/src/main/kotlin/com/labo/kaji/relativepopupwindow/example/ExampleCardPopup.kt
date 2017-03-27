package com.labo.kaji.relativepopupwindow.example

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import com.labo.kaji.relativepopupwindow.RelativePopupWindow

class ExampleCardPopup(context: Context) : RelativePopupWindow(context) {

    init {
        contentView = LayoutInflater.from(context).inflate(R.layout.popup_card, null)
        width = ViewGroup.LayoutParams.WRAP_CONTENT
        height = ViewGroup.LayoutParams.WRAP_CONTENT
        isFocusable = true
        isOutsideTouchable = true
        setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // Disable default animation for circular reveal
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            animationStyle = 0
        }
    }

    override fun showOnAnchor(anchor: View, vertPos: Int, horizPos: Int, x: Int, y: Int, fitInScreen: Boolean) {
        super.showOnAnchor(anchor, vertPos, horizPos, x, y, fitInScreen)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            circularReveal(anchor)
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun circularReveal(anchor: View) {
        contentView.apply {
            post {
                val myLocation = IntArray(2).apply { getLocationOnScreen(this) }
                val anchorLocation = IntArray(2).apply { anchor.getLocationOnScreen(this) }
                val cx = anchorLocation[0] - myLocation[0] + anchor.width/2
                val cy = anchorLocation[1] - myLocation[1] + anchor.height/2

                measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
                val dx = Math.max(cx, measuredWidth - cx)
                val dy = Math.max(cy, measuredHeight - cy)
                val finalRadius = Math.hypot(dx.toDouble(), dy.toDouble()).toFloat()
                ViewAnimationUtils.createCircularReveal(this, cx, cy, 0f, finalRadius).apply {
                    duration = 500
                    start()
                }
            }
        }
    }

}