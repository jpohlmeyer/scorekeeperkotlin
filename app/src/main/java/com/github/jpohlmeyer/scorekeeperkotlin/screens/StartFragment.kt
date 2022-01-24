package com.github.jpohlmeyer.scorekeeperkotlin.screens

import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.github.jpohlmeyer.scorekeeperkotlin.databinding.FragmentStartBinding
import com.github.jpohlmeyer.scorekeeperkotlin.screens.start.StartViewModel
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    private val viewModel: StartViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.gameList.forEach {
            val button = MaterialButton(requireContext())
            val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
            button.layoutParams = layoutParams
            button.text = it.gameTypeName
            button.setOnClickListener {
                // TODO choose which game, maybe gameService.setGame()?
                val action = StartFragmentDirections.actionStartFragmentToAddPlayersFragment()
                findNavController().navigate(action)
            }
            binding.gamelist.addView(button)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}