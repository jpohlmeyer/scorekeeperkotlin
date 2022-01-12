package com.github.jpohlmeyer.scorekeeperkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.jpohlmeyer.scorekeeperkotlin.databinding.ActivityMainBinding
import com.github.jpohlmeyer.scorekeeperkotlin.playerlist.*

class MainActivity : AppCompatActivity(), OnStartDragListener, OnListTouchListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var itemTouchHelper: ItemTouchHelper
    private lateinit var playerListAdapter: PlayerListAdapter
    private lateinit var playerList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playerList = binding.playerlist
        playerList.layoutManager = LinearLayoutManager(this)
        playerList.adapter = PlayerListAdapter(this)

        itemTouchHelper = ItemTouchHelper(PlayerNameTouchHelperCallback(this))
        itemTouchHelper.attachToRecyclerView(playerList)
    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        itemTouchHelper.startDrag(viewHolder)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        return playerListAdapter.itemMove(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        playerListAdapter.itemDismiss(position)
    }
}