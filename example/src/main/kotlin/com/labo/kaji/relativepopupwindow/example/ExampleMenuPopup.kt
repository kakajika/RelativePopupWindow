package com.labo.kaji.relativepopupwindow.example

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.view.*
import android.widget.TextView
import androidx.annotation.MenuRes
import androidx.appcompat.view.menu.MenuBuilder
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.circularreveal.CircularRevealCompat
import com.google.android.material.circularreveal.cardview.CircularRevealCardView
import com.labo.kaji.relativepopupwindow.RelativePopupWindow
import kotlinx.android.synthetic.main.popup_menu.view.*
import kotlin.math.hypot
import kotlin.math.max

class ExampleMenuPopup(context: Context) : RelativePopupWindow(context) {

    init {
        @SuppressLint("InflateParams")
        contentView = LayoutInflater.from(context).inflate(R.layout.popup_menu, null)
        width = ViewGroup.LayoutParams.WRAP_CONTENT
        height = ViewGroup.LayoutParams.WRAP_CONTENT
        isFocusable = true
        isOutsideTouchable = true
        setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // Disable default animation for circular reveal
        animationStyle = 0
    }

    fun inflateMenu(@MenuRes menuRes: Int) {
        contentView.popup_menu_list.run {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL).apply {
                setDrawable(ColorDrawable(Color.GRAY))
            })
            adapter = MenuListAdapter(context, menuRes)
        }
    }

    override fun showOnAnchor(anchor: View, vertPos: Int, horizPos: Int, x: Int, y: Int, fitInScreen: Boolean) {
        super.showOnAnchor(anchor, vertPos, horizPos, x, y, fitInScreen)
        circularReveal(anchor)
    }

    private fun circularReveal(anchor: View) {
        (contentView as CircularRevealCardView).run {
            post {
                val myLocation = IntArray(2).apply { getLocationOnScreen(this) }
                val anchorLocation = IntArray(2).apply { anchor.getLocationOnScreen(this) }
                val cx = anchorLocation[0] - myLocation[0]
                val cy = anchorLocation[1] - myLocation[1]
                val windowRect = Rect().apply { getWindowVisibleDisplayFrame(this) }

                measure(
                    makeDropDownMeasureSpec(this@ExampleMenuPopup.width, windowRect.width()),
                    makeDropDownMeasureSpec(this@ExampleMenuPopup.height, windowRect.height())
                )
                val dx = max(cx, measuredWidth - cx)
                val dy = max(cy, measuredHeight - cy)
                val finalRadius = hypot(dx.toFloat(), dy.toFloat())
                CircularRevealCompat.createCircularReveal(this, cx.toFloat(), cy.toFloat(), 0f, finalRadius).run {
                    duration = 500
                    start()
                }
            }
        }
    }

    companion object {
        private fun makeDropDownMeasureSpec(measureSpec: Int, maxSize: Int): Int {
            return View.MeasureSpec.makeMeasureSpec(
                getDropDownMeasureSpecSize(measureSpec, maxSize),
                getDropDownMeasureSpecMode(measureSpec)
            )
        }

        private fun getDropDownMeasureSpecSize(measureSpec: Int, maxSize: Int): Int {
            return when (measureSpec) {
                ViewGroup.LayoutParams.MATCH_PARENT -> maxSize
                else -> View.MeasureSpec.getSize(measureSpec)
            }
        }

        private fun getDropDownMeasureSpecMode(measureSpec: Int): Int {
            return when (measureSpec) {
                ViewGroup.LayoutParams.WRAP_CONTENT -> View.MeasureSpec.UNSPECIFIED
                else -> View.MeasureSpec.EXACTLY
            }
        }
    }

    class MenuListAdapter(val menu: Menu) : RecyclerView.Adapter<MenuItemViewHolder>() {
        @SuppressLint("RestrictedApi")
        constructor(context: Context, @MenuRes menuRes: Int) : this(
            MenuBuilder(context).apply { MenuInflater(context).inflate(menuRes, this) }
        )

        override fun getItemCount(): Int {
            return menu.size()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {
            return MenuItemViewHolder(parent)
        }

        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {
            holder.text1.text = menu.getItem(position).title
        }
    }

    class MenuItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater
            .from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
    ) {
        val text1: TextView = itemView.findViewById(android.R.id.text1)
    }
}
