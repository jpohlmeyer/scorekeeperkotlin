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
import com.github.jpohlmeyer.scorekeeperkotlin.model.SkullKingPlayer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SkullKingGameFragment : Fragment() {

    private var _binding: FragmentSkullKingGameBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SkullKingGameViewModel by viewModels()

    private var toggle = true

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
        binding.accordiontest1.setOnClickListener(this::toggleAccordion)
    }

    private fun toggleAccordion(view: View) {
        // todo save accordion state in viewmodel?
        val accordionIndex = binding.pointTable.indexOfChild(binding.accordiontest1)
        if (toggle) {
            for (i in viewModel.playerLiveData.indices) {
                binding.pointTable.getChildAt(accordionIndex + i + 1).visibility = View.GONE
            }
        } else {
            for (i in viewModel.playerLiveData.indices) {
                binding.pointTable.getChildAt(accordionIndex + i + 1).visibility = View.VISIBLE
            }
        }
        toggle = !toggle
    }

    private fun initTable() {
        val accordionIndex = binding.pointTable.indexOfChild(binding.accordiontest1)
        for (i in viewModel.playerLiveData.indices) {
            val tableRow = layoutInflater.inflate(R.layout.simple_game_row, binding.pointTable, false)
            val playerName = tableRow.findViewById<TextView>(R.id.name)
            val playerObserver = Observer<SkullKingPlayer> { player ->
                playerName.text = player.name
            }
            viewModel.playerLiveData[i].observe(viewLifecycleOwner, playerObserver)
            binding.pointTable.addView(tableRow, accordionIndex + i + 1)
        }
    }

//    private fun initTable() {
//        for (i in viewModel.playerLiveData.indices) {
//            val tableRow = layoutInflater.inflate(R.layout.simple_game_row, binding.pointTable, false)
//            val playerName = tableRow.findViewById<TextView>(R.id.name)
//            val totalPoints: TextView = tableRow.findViewById(R.id.totalpoints)
//            val newPoints: EditText = tableRow.findViewById(R.id.addpoints)
//            val submitButton: ImageButton = tableRow.findViewById(R.id.add_points_button)
//            newPoints.setOnEditorActionListener(OnEditorActionListener { _, actionId, _ ->
//                if (actionId == EditorInfo.IME_ACTION_DONE) {
//                    submitButton.performClick()
//                    return@OnEditorActionListener true
//                }
//                false
//            })
//            submitButton.setOnClickListener {
//                if (newPoints.text.isNotEmpty()) {
//                    viewModel.addPoints(i, (newPoints.text.toString()).toInt())
//                    newPoints.setText("")
//                }
//            }
//            binding.pointTable.addView(tableRow)
//            val playerObserver = Observer<SimplePlayer> { player ->
//                playerName.text = player.name
//                totalPoints.text = player.points.toString()
//            }
//            viewModel.playerLiveData[i].observe(viewLifecycleOwner, playerObserver)
//        }
//    }

    private fun endGameButtonOnClick(view: View) {
        val action = SkullKingGameFragmentDirections.actionSkullKingGameFragmentToStartFragment()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}