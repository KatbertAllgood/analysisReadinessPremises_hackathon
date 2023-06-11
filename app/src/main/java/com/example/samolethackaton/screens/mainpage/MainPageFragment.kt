package com.example.samolethackaton.screens.mainpage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.samolethackaton.R
import com.example.samolethackaton.databinding.FragmentMainPageBinding
import com.example.samolethackaton.screens.mainpage.adapters.MainPageDateAdapter
import com.example.samolethackaton.screens.mainpage.adapters.MainPageObjectsAdapter

class MainPageFragment : Fragment(R.layout.fragment_main_page) {
    private lateinit var binding : FragmentMainPageBinding
    private val viewModel : MainPageFragmentVM by viewModels()

    private val TAG = MainPageFragment::class.simpleName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainPageBinding.inflate(inflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        binding.fragmentMainPageRlCounting.visibility = View.GONE
        binding.fragmentMainPageTvChooseDate.visibility = View.VISIBLE
        binding.fragmentMainPageRvObjects.visibility = View.GONE
        binding.fragmentMainPagePbCounting.visibility = View.GONE


        viewModel.getAllObjects()

        viewModel.getAllDateLiveData().observe(viewLifecycleOwner) {

            val dateAdapter = MainPageDateAdapter(it, requireContext())

            dateAdapter.onItemClick = { date ->
                viewModel.changeDate(date.first)
                binding.fragmentMainPageTvDateObjects.text = requireContext().getString(
                    R.string.count_of_objects_with_date,
                    date.first
                )

                binding.fragmentMainPageRlCounting.visibility = View.VISIBLE
                binding.fragmentMainPageTvChooseDate.visibility = View.GONE
                binding.fragmentMainPageRvObjects.visibility = View.VISIBLE
                binding.fragmentMainPagePbCounting.visibility = View.VISIBLE
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

        viewModel.getProgressOnSelectedData().observe(viewLifecycleOwner) {

            binding.fragmentMainPagePbCounting.progress = it
        }

        viewModel.getTasksOnSelectedDateLiveData().observe(viewLifecycleOwner) {

            val objectsAdapter = MainPageObjectsAdapter(it, requireContext())

            objectsAdapter.onItemClick = { objectParams, numOfTask ->
                findNavController().navigate(
                    R.id.buildingFragment,
                    bundleOf(
                        "objectId" to objectParams.id_object,
                        "numOfTask" to numOfTask
                    )
                )
            }

            binding.apply {

                fragmentMainPageRvObjects.layoutManager = LinearLayoutManager(requireContext())
                fragmentMainPageRvObjects.isNestedScrollingEnabled = false
                fragmentMainPageRvObjects.adapter = objectsAdapter
            }
        }

    }

    override fun onPause() {
        super.onPause()

    }

}