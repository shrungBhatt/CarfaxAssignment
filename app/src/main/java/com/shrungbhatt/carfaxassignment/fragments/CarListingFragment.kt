package com.shrungbhatt.carfaxassignment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.shrungbhatt.carfaxassignment.adapters.CarListAdapter
import com.shrungbhatt.carfaxassignment.databinding.FragmentCarListingBinding
import com.shrungbhatt.carfaxassignment.viewmodels.CarListingFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CarListingFragment : Fragment() {

    private lateinit var binding: FragmentCarListingBinding
    private val viewModel: CarListingFragmentViewModel by viewModels()
    private val adapter = CarListAdapter()
    private var fetchJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCarListingBinding.inflate(inflater, container, false)
        binding.carList.adapter = adapter
        subscribeToUI()
        getCars()

        return binding.root
    }

    private fun subscribeToUI() {
        viewModel.cars.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun getCars() {
        fetchJob?.cancel()
        fetchJob = lifecycleScope.launch {
            viewModel.fetchCars()
        }
    }

}