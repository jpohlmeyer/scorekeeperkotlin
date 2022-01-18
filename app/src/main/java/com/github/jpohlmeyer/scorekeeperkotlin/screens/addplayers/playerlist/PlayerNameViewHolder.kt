package com.github.jpohlmeyer.scorekeeperkotlin.screens.addplayers.playerlist

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.jpohlmeyer.scorekeeperkotlin.databinding.DraggableStringListItemBinding

class PlayerNameViewHolder(private val binding: DraggableStringListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    private var textView: TextView = binding.string
    private var draghandleView: ImageView = binding.draghandle

    fun getTextView(): TextView {
        return textView
    }

    fun getDraghandleView(): ImageView {
        return draghandleView
    }
}