package com.github.jpohlmeyer.scorekeeperkotlin.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.jpohlmeyer.scorekeeperkotlin.R
import com.github.jpohlmeyer.scorekeeperkotlin.databinding.FragmentGameBinding
import com.github.jpohlmeyer.scorekeeperkotlin.screens.game.GameViewModel
import com.google.android.material.divider.MaterialDivider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameFragment : Fragment() {
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GameViewModel by viewModels()

    // TODO make this work for different game types?

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        populateTableWithPlayerNames()
        binding.endButton.setOnClickListener(this::endGameButtonOnClick)
    }

    private fun populateTableWithPlayerNames() {
        viewModel.simpleGame().playerList.forEach { player ->
            val tableRow = layoutInflater.inflate(R.layout.simple_game_row, binding.pointTable, false)
            tableRow.findViewById<TextView>(R.id.name).text = player.name
            val totalPoints: TextView = tableRow.findViewById(R.id.totalpoints)
            totalPoints.text = player.points.toString()
            val newPoints: EditText = tableRow.findViewById(R.id.addpoints)
            tableRow.findViewById<ImageButton>(R.id.add_points_button).setOnClickListener {
                if (newPoints.text.isNotEmpty()) {
                    // TODO do this with viewmodel
                    totalPoints.text =
                        ((totalPoints.text.toString()).toInt() + (newPoints.text.toString()).toInt()).toString()
                    newPoints.setText("")
                }
            }
            binding.pointTable.addView(tableRow)
        }
    }

    private fun endGameButtonOnClick(view: View) {
        val action = GameFragmentDirections.actionGameFragmentToStartFragment()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}