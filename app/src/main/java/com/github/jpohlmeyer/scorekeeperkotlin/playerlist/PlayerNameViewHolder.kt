package com.github.jpohlmeyer.scorekeeperkotlin.playerlist

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.jpohlmeyer.scorekeeperkotlin.R

class PlayerNameViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var textView: TextView = itemView.findViewById(R.id.playername)
    private var draghandleView: ImageView = itemView.findViewById(R.id.draghandle)

    fun getTextView(): TextView {
        return textView
    }

    fun getDraghandleView(): ImageView {
        return draghandleView
    }
}