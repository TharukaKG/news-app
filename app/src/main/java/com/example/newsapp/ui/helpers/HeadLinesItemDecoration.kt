package com.example.newsapp.ui.helpers

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HeadLinesItemDecoration(private val spaceSize: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            left = if (parent.getChildAdapterPosition(view) == 0) {
                40
            }else{
                spaceSize
            }

            right = if((parent.adapter?.itemCount?.minus(1))==parent.getChildAdapterPosition(view)){
                40
            }else{
                spaceSize
            }
            bottom = spaceSize
        }
    }
}