package com.github.jpohlmeyer.scorekeeperkotlin.screens.skullkinggame

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.github.jpohlmeyer.scorekeeperkotlin.R
import com.github.jpohlmeyer.scorekeeperkotlin.databinding.FragmentSkullKingGameBinding
import com.github.jpohlmeyer.scorekeeperkotlin.model.skullkinggame.SkullKingGame
import com.github.jpohlmeyer.scorekeeperkotlin.model.skullkinggame.SkullKingPlayer
import com.google.android.material.divider.MaterialDivider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SkullKingGameFragment : Fragment() {

    private var _binding: FragmentSkullKingGameBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SkullKingGameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSkullKingGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initTable()
        binding.endButton.setOnClickListener(this::endGameButtonOnClick)
    }

    // todo toggle indicator on each round?
    // TODO end round button on bottom? save values on switching rounds
    private fun initTable() {
        for (roundIndex in 1..SkullKingGame.NUMBER_OF_ROUNDS) {
            val roundRow =
                layoutInflater.inflate(R.layout.skull_king_game_round, binding.pointTable, false)
            roundRow.findViewById<TextView>(R.id.roundtext).text = "Round $roundIndex"
            binding.pointTable.addView(roundRow)

            val roundHeadRow =
                layoutInflater.inflate(R.layout.skull_king_game_head_row, binding.pointTable, false)
            roundHeadRow.visibility = View.GONE
            binding.pointTable.addView(roundHeadRow)

            var playerRow: View? = null
            for (playerIndex in viewModel.playerLiveData.indices) {
                playerRow =
                    layoutInflater.inflate(R.layout.skull_king_game_row, binding.pointTable, false)
                playerRow.visibility = View.GONE

                val playerName = playerRow.findViewById<TextView>(R.id.name)
                val playerObserver = Observer<SkullKingPlayer> { player ->
                    playerName.text = player.name
                }
                viewModel.playerLiveData[playerIndex].observe(viewLifecycleOwner, playerObserver)

                val tricksGuessed = playerRow.findViewById<EditText>(R.id.tricks_guessed)
                tricksGuessed.setOnEditorActionListener { _, actionId, _ ->
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        if (tricksGuessed.text.isNotEmpty()) {
                            viewModel.setTricksGuessed(
                                playerIndex,
                                (tricksGuessed.text.toString()).toInt(),
                                roundIndex
                            )
                        }
                    }
                    false
                }

                val tricksGot = playerRow.findViewById<EditText>(R.id.tricks_got)
                tricksGot.setOnEditorActionListener { _, actionId, _ ->
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        if (tricksGot.text.isNotEmpty()) {
                            viewModel.setTricksGot(
                                playerIndex,
                                (tricksGot.text.toString()).toInt(),
                                roundIndex
                            )
                        }
                    }
                    false
                }

                val bonus = playerRow.findViewById<EditText>(R.id.bonus)
                bonus.setOnEditorActionListener { _, actionId, _ ->
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        if (bonus.text.isNotEmpty()) {
                            viewModel.setBonus(
                                playerIndex,
                                (bonus.text.toString()).toInt(),
                                roundIndex
                            )
                        }
                    }
                    false
                }

                binding.pointTable.addView(playerRow)
            }
            playerRow?.setPadding(
                0,
                0,
                0,
                resources.getDimensionPixelSize(R.dimen.skull_king_last_player_padding)
            )

            val divider = MaterialDivider(requireContext())
            binding.pointTable.addView(divider)

            roundRow.setOnClickListener {
                toggleRound(roundIndex)
            }
        }
        // toggle round 1
        binding.pointTable.getChildAt(0).callOnClick()
    }

    private fun toggleRound(roundNumber: Int) {
        val rowIndexOffset = viewModel.playerLiveData.size + 3

        // hide all rows
        for (round in 0 until SkullKingGame.NUMBER_OF_ROUNDS) {
            for (row in round*rowIndexOffset+1 until (round+1)*rowIndexOffset) {
                binding.pointTable.getChildAt(row).visibility = View.GONE
            }
        }

        // show clicked row
        val rowIndex = (roundNumber - 1) * rowIndexOffset
        for (rowsInsideRound in rowIndex until rowIndex+rowIndexOffset) {
            binding.pointTable.getChildAt(rowsInsideRound).visibility = View.VISIBLE
        }
    }

    private fun endGameButtonOnClick(view: View) {
        val action = SkullKingGameFragmentDirections.actionSkullKingGameFragmentToStartFragment()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}