package com.example.samolethackaton.screens.building

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.samolethackaton.R
import com.example.samolethackaton.databinding.FragmentBuildingBinding

class BuildingFragment : Fragment(R.layout.fragment_building) {
    private lateinit var binding : FragmentBuildingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBuildingBinding.inflate(inflater)
        return binding.root
    }

}