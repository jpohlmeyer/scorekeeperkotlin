package com.github.jpohlmeyer.scorekeeperkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.jpohlmeyer.scorekeeperkotlin.databinding.ActivityMainBinding
import com.github.jpohlmeyer.scorekeeperkotlin.playerlist.OnStartDragListener
import com.github.jpohlmeyer.scorekeeperkotlin.playerlist.PlayerListAdapter
import com.github.jpohlmeyer.scorekeeperkotlin.playerlist.PlayerNameTouchHelperCallback

class MainActivity : AppCompatActivity(), OnStartDragListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var itemTouchHelper: ItemTouchHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.playerlist.layoutManager = LinearLayoutManager(this)

        val adapter = PlayerListAdapter(this)
        binding.playerlist.adapter = adapter

        val callback: ItemTouchHelper.Callback = PlayerNameTouchHelperCallback(adapter)
        itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.playerlist)
    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        itemTouchHelper.startDrag(viewHolder)
    }
}