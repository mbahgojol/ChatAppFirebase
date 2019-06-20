package com.bpdsulteng.mobile.utils

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class HorizontalSpaceItemDecoration(private val margin: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.top = margin
        outRect.bottom = margin
        outRect.left = margin
        if (parent.getChildAdapterPosition(view) == parent.adapter.itemCount - 1) {
            outRect.right = margin
        }
    }
}