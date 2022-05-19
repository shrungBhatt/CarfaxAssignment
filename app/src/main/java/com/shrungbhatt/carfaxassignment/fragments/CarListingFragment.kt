package com.shrungbhatt.carfaxassignment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.shrungbhatt.carfaxassignment.adapters.CarListAdapter
import com.shrungbhatt.carfaxassignment.databinding.FragmentCarListingBinding
import com.shrungbhatt.carfaxassignment.util.EventType
import com.shrungbhatt.carfaxassignment.viewmodels.CarListingFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CarListingFragment : Fragment() {

    private lateinit var binding: FragmentCarListingBinding
    private val viewModel: CarListingFragmentViewModel by viewModels()
    private val adapter = CarListAdapter()
    private var fetchJob: Job? = null
    private var snackBar: Snackbar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCarListingBinding.inflate(inflater, container, false)
        binding.carList.adapter = adapter
        binding.viewModel = viewModel

        setupSwipeRefresh()
        subscribeToUI()
        observeEvents()
        getCars()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        snackBar = Snackbar.make(view, "", Snackbar.LENGTH_SHORT)
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            getCars()
        }
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

    private fun observeEvents() {
        viewModel.observeEvents()
        viewModel.errors.observe(viewLifecycleOwner){
            snackBar?.setText(it)?.show()
        }
    }

}