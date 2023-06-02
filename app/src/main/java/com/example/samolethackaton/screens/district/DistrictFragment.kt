package com.example.samolethackaton.screens.district

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.samolethackaton.R
import com.example.samolethackaton.databinding.FragmentDistrictBinding

class DistrictFragment : Fragment(R.layout.fragment_district) {
    private lateinit var binding : FragmentDistrictBinding
    private val viewModel : DistrictFragmentVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDistrictBinding.inflate(inflater)
        return binding.root
    }

}