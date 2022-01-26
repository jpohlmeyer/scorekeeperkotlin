package com.github.jpohlmeyer.scorekeeperkotlin.screens.simplegame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.github.jpohlmeyer.scorekeeperkotlin.R
import com.github.jpohlmeyer.scorekeeperkotlin.databinding.FragmentSimpleGameBinding
import com.github.jpohlmeyer.scorekeeperkotlin.model.Player
import com.github.jpohlmeyer.scorekeeperkotlin.model.SimplePlayer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SimpleGameFragment : Fragment() {
    private var _binding: FragmentSimpleGameBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SimpleGameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSimpleGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        populateTableWithPlayerNames()
        binding.endButton.setOnClickListener(this::endGameButtonOnClick)
    }

    private fun populateTableWithPlayerNames() {
        for (i in viewModel.playerLiveData.indices) {
            val tableRow = layoutInflater.inflate(R.layout.simple_game_row, binding.pointTable, false)
            val playerName = tableRow.findViewById<TextView>(R.id.name)
            val totalPoints: TextView = tableRow.findViewById(R.id.totalpoints)
            val newPoints: EditText = tableRow.findViewById(R.id.addpoints)
            tableRow.findViewById<ImageButton>(R.id.add_points_button).setOnClickListener {
                if (newPoints.text.isNotEmpty()) {
                    viewModel.addPoints(i, (newPoints.text.toString()).toInt())
                    newPoints.setText("")
                }
            }
            binding.pointTable.addView(tableRow)
            val playerObserver = Observer<SimplePlayer> { player ->
                playerName.text = player.name
                totalPoints.text = player.points.toString()
            }
            viewModel.playerLiveData[i].observe(viewLifecycleOwner, playerObserver)
        }
    }

    private fun endGameButtonOnClick(view: View) {
        val action = SimpleGameFragmentDirections.actionGameFragmentToStartFragment()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}