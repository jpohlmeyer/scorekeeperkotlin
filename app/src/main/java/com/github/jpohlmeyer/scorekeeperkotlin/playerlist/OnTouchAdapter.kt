package com.github.jpohlmeyer.scorekeeperkotlin.playerlist

interface OnTouchAdapter {
    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean

    fun onItemDismiss(position: Int)
}