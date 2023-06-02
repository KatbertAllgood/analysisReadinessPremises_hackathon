package com.example.samolethackaton.screens.section

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.samolethackaton.R
import com.example.samolethackaton.databinding.FragmentSectionBinding

class SectionFragment : Fragment(R.layout.fragment_section) {
    lateinit var binding : FragmentSectionBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSectionBinding.inflate(inflater)
        return binding.root
    }

}