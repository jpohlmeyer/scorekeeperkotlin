package com.github.jpohlmeyer.scorekeeperkotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.jpohlmeyer.scorekeeperkotlin.databinding.FragmentAddPlayersBinding
import com.github.jpohlmeyer.scorekeeperkotlin.playerlist.OnListTouchListener
import com.github.jpohlmeyer.scorekeeperkotlin.playerlist.OnStartDragListener
import com.github.jpohlmeyer.scorekeeperkotlin.playerlist.PlayerListAdapter
import com.github.jpohlmeyer.scorekeeperkotlin.playerlist.PlayerNameTouchHelperCallback

class AddPlayersFragment : Fragment(), OnStartDragListener, OnListTouchListener {

    private var _binding: FragmentAddPlayersBinding? = null
    private val binding get() = _binding!!
    private lateinit var playerlistView: RecyclerView
    private lateinit var itemTouchHelper: ItemTouchHelper
    private lateinit var playerListAdapter: PlayerListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddPlayersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        playerlistView = binding.playerlist
        playerlistView.layoutManager = LinearLayoutManager(context)
        playerlistView.adapter = PlayerListAdapter(this)

        itemTouchHelper = ItemTouchHelper(PlayerNameTouchHelperCallback(this))
        itemTouchHelper.attachToRecyclerView(playerlistView)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}