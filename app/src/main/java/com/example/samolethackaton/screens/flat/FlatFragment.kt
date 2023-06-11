package com.example.samolethackaton.screens.flat

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.utils.Constants
import com.example.samolethackaton.R
import com.example.samolethackaton.databinding.FragmentFlatBinding
import com.example.samolethackaton.screens.flat.adapter.ScoreAdapter

class FlatFragment : Fragment(R.layout.fragment_flat) {
    private lateinit var binding : FragmentFlatBinding
    private val viewModel : FlatFragmentVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFlatBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onResume() {
        super.onResume()

        binding.fragmentFlatTvAddress.text = requireArguments().getString("address")

        viewModel.getScore()

        viewModel.getScoreLiveData().observe(viewLifecycleOwner) {

            val cleanAdapter = ScoreAdapter(it.data.clean)
            val roughAdapter = ScoreAdapter(it.data.rough)
            val otherAdapter = ScoreAdapter(it.data.other)

            listOf(
                binding.fragmentFlatRvClean,
                binding.fragmentFlatRvRough,
                binding.fragmentFlatRvOther,
            ).forEach {

                it.apply {

//                    layoutManager = GridLayoutManager(requireContext(), 3)
                    layoutManager = LinearLayoutManager(requireContext())
                    isNestedScrollingEnabled = false
                }
            }

            binding.apply {

                fragmentFlatRvClean.adapter = cleanAdapter
                fragmentFlatRvRough.adapter = roughAdapter
                fragmentFlatRvOther.adapter = otherAdapter
            }
        }

    }
}