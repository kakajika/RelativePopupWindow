package com.labo.kaji.relativepopupwindow.example

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
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
    }
}