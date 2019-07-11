package com.labo.kaji.relativepopupwindow.example

import android.os.Bundle
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.ArrayRes
import androidx.appcompat.app.AppCompatActivity
import com.labo.kaji.relativepopupwindow.RelativePopupWindow.HorizontalPosition
import com.labo.kaji.relativepopupwindow.RelativePopupWindow.VerticalPosition
import kotlinx.android.synthetic.main.activity_example.*

class ExampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)

        spinner_vertical.adapter   = createSpinnerAdapter(R.array.vertical_positions)
        spinner_horizontal.adapter = createSpinnerAdapter(R.array.horizontal_positions)
        spinner_width.adapter      = createSpinnerAdapter(R.array.width)
        spinner_height.adapter     = createSpinnerAdapter(R.array.height)

        button1.setOnClickListener { view ->
            val popup = ExampleCardPopup(view.context)
            popup.width = when (spinner_width.selectedItemPosition) {
                0 -> ViewGroup.LayoutParams.WRAP_CONTENT
                1 -> ViewGroup.LayoutParams.MATCH_PARENT
                2 -> TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80f, resources.displayMetrics).toInt()
                3 -> TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 240f, resources.displayMetrics).toInt()
                4 -> TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 480f, resources.displayMetrics).toInt()
                else -> throw IllegalStateException()
            }
            popup.height = when (spinner_height.selectedItemPosition) {
                0 -> ViewGroup.LayoutParams.WRAP_CONTENT
                1 -> ViewGroup.LayoutParams.MATCH_PARENT
                2 -> TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80f, resources.displayMetrics).toInt()
                3 -> TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 240f, resources.displayMetrics).toInt()
                4 -> TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 480f, resources.displayMetrics).toInt()
                else -> throw IllegalStateException()
            }
            val vertPos = when (spinner_vertical.selectedItemPosition) {
                0 -> VerticalPosition.ABOVE
                1 -> VerticalPosition.ALIGN_BOTTOM
                2 -> VerticalPosition.CENTER
                3 -> VerticalPosition.ALIGN_TOP
                4 -> VerticalPosition.BELOW
                else -> throw IllegalStateException()
            }
            val horizPos = when (spinner_horizontal.selectedItemPosition) {
                0 -> HorizontalPosition.LEFT
                1 -> HorizontalPosition.ALIGN_RIGHT
                2 -> HorizontalPosition.CENTER
                3 -> HorizontalPosition.ALIGN_LEFT
                4 -> HorizontalPosition.RIGHT
                else -> throw IllegalStateException()
            }
            val fitInScreen = checkbox_fit_in_screen.isChecked
            popup.showOnAnchor(view, vertPos, horizPos, fitInScreen)
        }
    }

    private fun createSpinnerAdapter(@ArrayRes itemsResId: Int): ArrayAdapter<String> {
        return ArrayAdapter<String>(this, android.R.layout.simple_spinner_item).apply {
            addAll(*resources.getStringArray(itemsResId))
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
    }

}
