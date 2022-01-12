package com.github.jpohlmeyer.scorekeeperkotlin.playerlist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.jpohlmeyer.scorekeeperkotlin.R
import com.github.jpohlmeyer.scorekeeperkotlin.databinding.PlayerNameBinding
import java.util.*
import kotlin.collections.ArrayList

class PlayerListAdapter(private val onStartDragListener: OnStartDragListener) : RecyclerView.Adapter<PlayerNameViewHolder>(), OnTouchAdapter {

    private val STRINGS = arrayOf(
        "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
        "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
        "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
        "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"
    )

    private val items: MutableList<String?> = ArrayList()

    init {
        items.addAll(Arrays.asList(*STRINGS))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerNameViewHolder {
        val binding = PlayerNameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayerNameViewHolder(binding)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: PlayerNameViewHolder, position: Int) {
        holder.getTextView().setText(items[position])
        holder.getDraghandleView().setOnTouchListener { view: View?, event: MotionEvent ->
            if (event.actionMasked == MotionEvent.ACTION_DOWN) {
                onStartDragListener.onStartDrag(holder)
            }
            false
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        Collections.swap(items, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onItemDismiss(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }


}