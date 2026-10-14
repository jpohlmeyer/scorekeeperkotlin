package com.github.jpohlmeyer.scorekeeperkotlin.screens.selectplayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.jpohlmeyer.scorekeeperkotlin.R
import com.github.jpohlmeyer.scorekeeperkotlin.databinding.FragmentSelectPlayerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectPlayerFragment : Fragment() {

    private var _binding: FragmentSelectPlayerBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SelectPlayerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectPlayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.selectPlayerView.onTouchEventListener = { pointerId, x, y, isRemoved ->
            viewModel.updateTouch(pointerId, x, y, isRemoved)
        }

        viewModel.touchPoints.observe(viewLifecycleOwner) { points ->
            val selectedId = viewModel.selectedPointerId.value
            binding.selectPlayerView.updateData(points, selectedId)

            if (points.isNotEmpty()) {
                binding.instructionsTextView.visibility = View.GONE
            } else if (selectedId == null) {
                binding.instructionsTextView.visibility = View.VISIBLE
                binding.instructionsTextView.text = getString(R.string.select_player_instructions)
            }
        }

        viewModel.selectedPointerId.observe(viewLifecycleOwner) { selectedId ->
            val points = viewModel.touchPoints.value.orEmpty()
            binding.selectPlayerView.updateData(points, selectedId)

            if (selectedId != null) {
                binding.resetButton.visibility = View.VISIBLE
            } else {
                binding.resetButton.visibility = View.GONE
                if (points.isEmpty()) {
                    binding.instructionsTextView.visibility = View.VISIBLE
                    binding.instructionsTextView.text = getString(R.string.select_player_instructions)
                }
            }
        }

        binding.resetButton.setOnClickListener {
            viewModel.resetSelection()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
