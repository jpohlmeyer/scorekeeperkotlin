package com.github.jpohlmeyer.scorekeeperkotlin.screens.addplayers.playerlist

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.jpohlmeyer.scorekeeperkotlin.databinding.DraggableStringListItemBinding

class PlayerNameViewHolder(binding: DraggableStringListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    var textView: TextView = binding.string
    var draghandleView: ImageView = binding.draghandle
}