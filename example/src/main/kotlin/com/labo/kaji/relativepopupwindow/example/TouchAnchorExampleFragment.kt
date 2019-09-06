package com.labo.kaji.relativepopupwindow.example

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PointF
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.ContextThemeWrapper
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.labo.kaji.relativepopupwindow.RelativePopupWindow
import kotlinx.android.synthetic.main.fragment_touch_anchor_example.*

class TouchAnchorExampleFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater
            .cloneInContext(ContextThemeWrapper(inflater.context, R.style.AppTheme))
            .inflate(R.layout.fragment_touch_anchor_example, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        touch_anchor_example_list.run {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = TouchAnchorListAdapter()
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL).apply {
                setDrawable(ColorDrawable(Color.GRAY))
            })
        }
    }

    private class TouchAnchorListAdapter : RecyclerView.Adapter<TouchAnchorViewHolder>() {
        override fun getItemCount(): Int {
            return 100
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TouchAnchorViewHolder {
            return TouchAnchorViewHolder(parent)
        }

        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(holder: TouchAnchorViewHolder, position: Int) {
            holder.text1.text = "Item $position"
            holder.itemView.setOnClickPositionListener { v, x, y ->
                val popup = ExampleMenuPopup(v.context).apply {
                    width = ViewGroup.LayoutParams.WRAP_CONTENT
                    height = ViewGroup.LayoutParams.WRAP_CONTENT
                    inflateMenu(R.menu.touch_anchor_item_menu)
                }
                val vertPos = RelativePopupWindow.VerticalPosition.ALIGN_TOP
                val horizPos = RelativePopupWindow.HorizontalPosition.ALIGN_LEFT
                popup.showOnAnchor(v, vertPos, horizPos, x.toInt(), y.toInt(), true)
            }
        }
    }

    private class TouchAnchorViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater
            .from(parent.context)
            .inflate(android.R.layout.simple_list_item_2, parent, false)
    ) {
        init {
            val background = TypedValue()
            itemView.context.theme.resolveAttribute(android.R.attr.selectableItemBackground, background, true)
            itemView.setBackgroundResource(background.resourceId)
        }
        val text1: TextView = itemView.findViewById(android.R.id.text1)
    }
}

fun View.setOnClickPositionListener(listener: (v: View, x: Float, y: Float) -> Unit) {
    var tapPosition = PointF()
    setOnTouchListener { _, event ->
        if (event.actionMasked == MotionEvent.ACTION_DOWN) {
            tapPosition = PointF(event.x, event.y)
        }
        false
    }
    setOnClickListener { v ->
        listener(v, tapPosition.x, tapPosition.y)
    }
}
