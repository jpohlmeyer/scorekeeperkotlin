package com.github.jpohlmeyer.scorekeeperkotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.jpohlmeyer.scorekeeperkotlin.databinding.FragmentStartBinding
import com.github.jpohlmeyer.scorekeeperkotlin.playerlist.PlayerListAdapter
import com.github.jpohlmeyer.scorekeeperkotlin.playerlist.PlayerNameTouchHelperCallback

class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!
    private lateinit var gamelistView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        gamelistView = binding.gamelist
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}