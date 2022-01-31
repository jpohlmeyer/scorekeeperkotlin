package com.github.jpohlmeyer.scorekeeperkotlin.screens.skullkinggame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    private var toggle = Array(SkullKingGame.NUMBER_OF_ROUNDS) { false }

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
    // todo mermaid/skullking bonus?
    // TODO add end round button?
    private fun initTable() {
        for (roundIndex in 1..SkullKingGame.NUMBER_OF_ROUNDS) {
            val roundRow = layoutInflater.inflate(R.layout.skull_king_game_round, binding.pointTable, false)
            roundRow.findViewById<TextView>(R.id.roundtext).text = "Round $roundIndex"
            binding.pointTable.addView(roundRow)

            val roundHeadRow = layoutInflater.inflate(R.layout.skull_king_game_head_row, binding.pointTable, false)
            roundHeadRow.visibility = View.GONE
            binding.pointTable.addView(roundHeadRow)

            for (playerIndex in viewModel.playerLiveData.indices) {
                val playerRow = layoutInflater.inflate(R.layout.skull_king_game_row, binding.pointTable, false)
                playerRow.visibility = View.GONE

                val playerName = playerRow.findViewById<TextView>(R.id.name)
                val playerObserver = Observer<SkullKingPlayer> { player ->
                    playerName.text = player.name
                }
                viewModel.playerLiveData[playerIndex].observe(viewLifecycleOwner, playerObserver)

                binding.pointTable.addView(playerRow)
            }
            val divider = MaterialDivider(requireContext())
            binding.pointTable.addView(divider)

            roundRow.setOnClickListener {
                val roundRowTableIndex = binding.pointTable.indexOfChild(roundRow)
                if (toggle[roundIndex - 1]) {
                    for (i in 1..(viewModel.playerLiveData.size + 1)) {
                        binding.pointTable.getChildAt(roundRowTableIndex + i).visibility = View.GONE
                    }
                } else {
                    for (i in 1..(viewModel.playerLiveData.size + 1)) {
                        binding.pointTable.getChildAt(roundRowTableIndex + i).visibility = View.VISIBLE
                    }
                }
                toggle[roundIndex - 1] = !toggle[roundIndex - 1]
            }
        }
        // toggle round 1
        binding.pointTable.getChildAt(0).callOnClick()
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