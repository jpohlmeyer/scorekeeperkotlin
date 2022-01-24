package com.github.jpohlmeyer.scorekeeperkotlin.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import com.github.jpohlmeyer.scorekeeperkotlin.R
import com.github.jpohlmeyer.scorekeeperkotlin.databinding.FragmentGameBinding
import com.github.jpohlmeyer.scorekeeperkotlin.screens.game.GameViewModel
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
    }

    fun populateTableWithPlayerNames() {
        // TODO items as XML? for different gametypes?
        val tableRow = TableRow(context)
        tableRow.background = ResourcesCompat.getDrawable(requireContext().resources, R.drawable.border, null)
        viewModel.simpleGame().playerList.forEach { player ->
            val nameTextView = TextView(context)
            nameTextView.text = player.name
            nameTextView.background = ResourcesCompat.getDrawable(requireContext().resources, R.drawable.border, null)
            tableRow.addView(nameTextView)
        }
        binding.pointTable.addView(tableRow)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}