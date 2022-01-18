package com.github.jpohlmeyer.scorekeeperkotlin.screens.addplayers.playerlist

interface OnListTouchListener {
    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean

    fun onItemDismiss(position: Int)
}