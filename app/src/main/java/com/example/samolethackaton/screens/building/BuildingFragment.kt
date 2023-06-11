package com.example.samolethackaton.screens.building

import android.content.Intent
import android.net.Uri
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
import com.example.domain.utils.Constants
import com.example.samolethackaton.R
import com.example.samolethackaton.databinding.FragmentBuildingBinding
import com.example.samolethackaton.screens.building.adapters.BuildingFragmentFloorAdapter
import com.example.samolethackaton.screens.building.adapters.BuildingFragmentSectionsAdapter

class BuildingFragment : Fragment(R.layout.fragment_building) {
    private lateinit var binding : FragmentBuildingBinding
    private val viewModel : BuildingFragmentVM by viewModels()

    private val TAG = BuildingFragment::class.simpleName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBuildingBinding.inflate(inflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.objectId = requireArguments().getInt("objectId")
        viewModel.numOfTask = requireArguments().getString("numOfTask").toString()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragmentBuildingTvNumOfTask.text = viewModel.numOfTask

        if (viewModel.isCameraPresentInPhone(requireActivity())){
            Log.d(TAG, "Camera is detected")
            viewModel.getCameraPermission(requireActivity(), requireContext())
        } else {
            Log.d(TAG, "Camera is NOT detected")
        }

        binding.fragmentBuildingIvArrowBack.setOnClickListener {

            findNavController().popBackStack()
        }

    }

    override fun onResume() {
        super.onResume()

        viewModel.getObjectById(viewModel.objectId.toString())

        viewModel.getAllSections(viewModel.objectId)

        viewModel.getObjectParamsLiveData().observe(viewLifecycleOwner) {

            binding.apply {

                fragmentBuildingPbCounting.progress = it.general_readiness
                fragmentBuildingTvAddress.text = requireContext().getString(
                    R.string.address_constructor,
                    it.street,
                    it.building
                )

            }
        }

        viewModel.getSectionsListLiveData().observe(viewLifecycleOwner) {

            Log.d(TAG, "SECTION LIST: $it")

            val sectionAdapter = BuildingFragmentSectionsAdapter(it)

            sectionAdapter.onItemClick = {
                viewModel.changeSection(it.id_section)
            }

            binding.fragmentBuildingRvSections.apply {

                layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                isNestedScrollingEnabled = false
                adapter = sectionAdapter
            }
        }

        viewModel.getSelectedSectionLiveData().observe(viewLifecycleOwner) {

            val floorList : MutableList<Int> = mutableListOf()

            for (i in 1..it.count_floor_house) {

                floorList.add(i)
            }

            Log.d(TAG, "COUNT_OF_FLOORS: $floorList")

            val floorAdapter = BuildingFragmentFloorAdapter(floorList, requireContext())

            floorAdapter.onRecordClick = {
                viewModel.recordVideo(requireActivity())

            }

            floorAdapter.onChooseClick = {
                viewModel.selectVideoFromGallery(requireActivity())
            }

            floorAdapter.onFlatClick = {

                findNavController().navigate(
                    R.id.flatFragment,
                    bundleOf(
                        "address" to binding.fragmentBuildingTvAddress.text
                    )
                )
            }

//            floorAdapter.onScoreBlick = {
//
//                val url = Constants.TABLE_URL
//                val i = Intent(Intent.ACTION_VIEW)
//                i.data = Uri.parse(url)
//                startActivity(i)
//            }

            binding.fragmentBuildingRvFloors.apply {

                layoutManager = LinearLayoutManager(requireContext())
                isNestedScrollingEnabled = false
                adapter = floorAdapter
            }
        }
    }
}