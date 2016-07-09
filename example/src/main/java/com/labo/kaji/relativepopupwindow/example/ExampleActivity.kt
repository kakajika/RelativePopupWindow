package com.labo.kaji.relativepopupwindow.example

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.labo.kaji.relativepopupwindow.RelativePopupWindow
import com.labo.kaji.relativepopupwindow.RelativePopupWindow.HorizontalPosition
import com.labo.kaji.relativepopupwindow.RelativePopupWindow.VerticalPosition
import kotlinx.android.synthetic.main.activity_example.*

class ExampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)

        spinner_vertical.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item).apply {
            addAll(*resources.getStringArray(R.array.vertical_positions))
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        spinner_horizontal.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item).apply {
            addAll(*resources.getStringArray(R.array.horizontal_positions))
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        button1.setOnClickListener { view ->
            @VerticalPosition val vertPos = spinner_vertical.selectedItemPosition
            @HorizontalPosition val horizPos = spinner_horizontal.selectedItemPosition
            CardPopup(view.context).showOnAnchor(view, vertPos, horizPos)
        }
    }

    private class CardPopup(context: Context) : RelativePopupWindow(context) {
        init {
            contentView = LayoutInflater.from(context).inflate(R.layout.popup_card, null)
            width = ViewGroup.LayoutParams.WRAP_CONTENT
            height = ViewGroup.LayoutParams.WRAP_CONTENT
            isFocusable = true
            isOutsideTouchable = true
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

    }

}
