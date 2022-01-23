package com.github.jpohlmeyer.scorekeeperkotlin.screens.addplayers.playerlist

interface OnTouchListChangedListener {
    fun onItemMove(fromPosition: Int, toPosition: Int)

    fun onItemDismiss(position: Int)
}