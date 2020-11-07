package com.nmproduction.jajanyuu.ui.common

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MyListFoodDecoration(private val space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view) // item position

        val column: Int = position % 4 // item column
        outRect.left = space - column * space / 4 // spacing - column * ((1f / spanCount) * spacing)
        outRect.right = (column + 1) * space / 4 // (column + 1) * ((1f / spanCount) * spacing)

        if (position < 4) { // top edge
            outRect.top = space
        }
        outRect.bottom = space // item bottom

//        if (parent.getChildAdapterPosition(view) % 2 == 0) {
//            outRect.right = space
//            outRect.left = space * 2
//        } else if (parent.getChildAdapterPosition(view) % 2 == 1) {
//            outRect.left = space
//            outRect.right = space * 2
//        }
//        if (parent.getChildAdapterPosition(view) == 0 || parent.getChildAdapterPosition(view) == 1) {
//            outRect.top = space * 2
//        }
//        outRect.bottom = space * 2
    }
}