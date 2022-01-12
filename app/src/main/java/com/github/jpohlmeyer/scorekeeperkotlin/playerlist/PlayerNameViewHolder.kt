package com.github.jpohlmeyer.scorekeeperkotlin.playerlist

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.jpohlmeyer.scorekeeperkotlin.R
import com.github.jpohlmeyer.scorekeeperkotlin.databinding.PlayerNameBinding

class PlayerNameViewHolder(private val binding: PlayerNameBinding) : RecyclerView.ViewHolder(binding.root) {

    private var textView: TextView = binding.playername
    private var draghandleView: ImageView = binding.draghandle

    fun getTextView(): TextView {
        return textView
    }

    fun getDraghandleView(): ImageView {
        return draghandleView
    }
}