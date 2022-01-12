package com.github.jpohlmeyer.scorekeeperkotlin.playerlist

interface OnListTouchListener {
    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean

    fun onItemDismiss(position: Int)
}