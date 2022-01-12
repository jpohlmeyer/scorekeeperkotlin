package com.github.jpohlmeyer.scorekeeperkotlin.playerlist

import androidx.recyclerview.widget.RecyclerView

interface OnStartDragListener {
    fun onStartDrag(viewHolder: RecyclerView.ViewHolder)
}