package com.github.jpohlmeyer.scorekeeperkotlin.screens.endgame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.jpohlmeyer.scorekeeperkotlin.R
import com.github.jpohlmeyer.scorekeeperkotlin.databinding.FragmentGameEndBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameEndFragment : Fragment() {
    private var _binding: FragmentGameEndBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GameEndViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameEndBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateTable()
        binding.startNewGameButton.setOnClickListener {
            val action = GameEndFragmentDirections.actionGameEndFragmentToStartFragment()
            findNavController().navigate(action)
        }
    }

    private fun populateTable() {
        for (player in viewModel.players) {
            val tableRow = layoutInflater.inflate(R.layout.game_end_row, binding.pointTable, false)
            val playerName = tableRow.findViewById<TextView>(R.id.name)
            val totalPoints = tableRow.findViewById<TextView>(R.id.totalpoints)
            playerName.text = player.name
            totalPoints.text = player.points.toString()
            binding.pointTable.addView(tableRow)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
