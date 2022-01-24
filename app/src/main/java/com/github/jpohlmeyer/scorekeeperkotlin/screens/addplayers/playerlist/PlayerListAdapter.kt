package com.github.jpohlmeyer.scorekeeperkotlin.screens.addplayers.playerlist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.jpohlmeyer.scorekeeperkotlin.databinding.DraggableStringListItemBinding
import com.github.jpohlmeyer.scorekeeperkotlin.model.SimplePlayer
import java.util.*

class PlayerListAdapter(
    private val onStartDragListener: OnStartDragListener
    ) : RecyclerView.Adapter<PlayerNameViewHolder>() {

    private var playerList: MutableList<SimplePlayer> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerNameViewHolder {
        val binding = DraggableStringListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayerNameViewHolder(binding)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: PlayerNameViewHolder, position: Int) {
        holder.textView.text = playerList[position].name
        holder.draghandleView.setOnTouchListener { _, event: MotionEvent ->
            if (event.actionMasked == MotionEvent.ACTION_DOWN) {
                onStartDragListener.onStartDrag(holder)
            }
            return@setOnTouchListener false
        }
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    fun itemMove(fromPosition: Int, toPosition: Int) {
        Collections.swap(playerList, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    fun itemDismiss(position: Int) {
        playerList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun itemAdd(player: SimplePlayer) {
        playerList.add(player)
        notifyItemInserted(playerList.size - 1)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updatePlayerList(playerList: List<SimplePlayer>) {
        if (this.playerList != playerList) {
            this.playerList.clear()
            this.playerList.addAll(playerList)
            notifyDataSetChanged()
        }
    }


}