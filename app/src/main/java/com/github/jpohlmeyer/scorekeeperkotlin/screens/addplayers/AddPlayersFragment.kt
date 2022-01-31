package com.github.jpohlmeyer.scorekeeperkotlin.screens.addplayers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.jpohlmeyer.scorekeeperkotlin.databinding.FragmentAddPlayersBinding
import com.github.jpohlmeyer.scorekeeperkotlin.model.Player
import com.github.jpohlmeyer.scorekeeperkotlin.model.SimpleGame
import com.github.jpohlmeyer.scorekeeperkotlin.model.SkullKingGame
import com.github.jpohlmeyer.scorekeeperkotlin.screens.addplayers.playerlist.OnTouchListChangedListener
import com.github.jpohlmeyer.scorekeeperkotlin.screens.addplayers.playerlist.OnStartDragListener
import com.github.jpohlmeyer.scorekeeperkotlin.screens.addplayers.playerlist.PlayerListAdapter
import com.github.jpohlmeyer.scorekeeperkotlin.screens.addplayers.playerlist.PlayerNameTouchHelperCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.full.primaryConstructor

@AndroidEntryPoint
class AddPlayersFragment : Fragment(), OnStartDragListener, OnTouchListChangedListener {

    private var _binding: FragmentAddPlayersBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AddPlayersViewModel by viewModels()
    private lateinit var playerlistView: RecyclerView
    private lateinit var itemTouchHelper: ItemTouchHelper
    private lateinit var playerListAdapter: PlayerListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddPlayersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        playerlistView = binding.playerlist
        playerlistView.layoutManager = LinearLayoutManager(context)
        playerListAdapter = PlayerListAdapter(this)
        playerlistView.adapter = playerListAdapter

        itemTouchHelper = ItemTouchHelper(PlayerNameTouchHelperCallback(this))
        itemTouchHelper.attachToRecyclerView(playerlistView)


        val playerListObserver = Observer<List<Player>> { newList ->
            playerListAdapter.updatePlayerList(newList)
        }
        viewModel.playerLiveData.observe(viewLifecycleOwner, playerListObserver)

        binding.addPlayerButton.setOnClickListener(this::addPlayerOnClick)
        binding.startButton.setOnClickListener(this::startGameOnClick)
    }

    private fun addPlayerOnClick(view: View) {
        val player = viewModel.game.playerType.primaryConstructor!!.call(binding.addPlayerName.text.toString())
        onItemAdded(player)
        binding.addPlayerName.setText("")
    }

    // TODO make navigation to game mode nicer
    private fun startGameOnClick(view: View) {
        if (viewModel.game is SimpleGame) {
            val action = AddPlayersFragmentDirections.actionAddPlayersFragmentToSimpleGameFragment()
            findNavController().navigate(action)
        } else if (viewModel.game is SkullKingGame) {
            val action = AddPlayersFragmentDirections.actionAddPlayersFragmentToSkullKingGameFragment()
            findNavController().navigate(action)
        }
    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        itemTouchHelper.startDrag(viewHolder)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        playerListAdapter.itemMove(fromPosition, toPosition)
        viewModel.movePlayer(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        playerListAdapter.itemDismiss(position)
        viewModel.deletePlayer(position)
    }

    private fun onItemAdded(player: Player) {
        playerListAdapter.itemAdd(player)
        viewModel.addPlayer(player)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}