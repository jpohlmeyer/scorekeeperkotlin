package com.github.jpohlmeyer.scorekeeperkotlin.screens.addplayers.playerlist

import androidx.recyclerview.widget.RecyclerView

interface OnStartDragListener {
    fun onStartDrag(viewHolder: RecyclerView.ViewHolder)
}