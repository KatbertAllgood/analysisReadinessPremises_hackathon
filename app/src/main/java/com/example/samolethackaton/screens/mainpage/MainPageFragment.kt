package com.example.samolethackaton.screens.mainpage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.samolethackaton.R
import com.example.samolethackaton.databinding.FragmentMainPageBinding
import com.example.samolethackaton.screens.mainpage.adapters.MainPageDateAdapter
import com.example.samolethackaton.screens.mainpage.adapters.MainPageObjectsAdapter

class MainPageFragment : Fragment(R.layout.fragment_main_page) {
    private lateinit var binding : FragmentMainPageBinding
    private val viewModel : MainPageFragmentVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainPageBinding.inflate(inflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        viewModel.getAllObjects()

        viewModel.getAllDateLiveData().observe(viewLifecycleOwner) {

            val dateAdapter = MainPageDateAdapter(it, requireContext())

            dateAdapter.onItemClick = { date ->
                viewModel.changeDate(date.first)
            }

            binding.apply {

                fragmentMainPageRvDates.layoutManager =
                    LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                fragmentMainPageRvDates.isNestedScrollingEnabled = false
                fragmentMainPageRvDates.adapter = dateAdapter
            }
        }

        viewModel.getTasksOnSelectedDateLiveData().observe(viewLifecycleOwner) {

            val objectsAdapter = MainPageObjectsAdapter(it)

            binding.apply {

                fragmentMainPageRvObjects.layoutManager = LinearLayoutManager(requireContext())
                fragmentMainPageRvObjects.isNestedScrollingEnabled = false
                fragmentMainPageRvObjects.adapter = objectsAdapter
            }
        }

    }

}