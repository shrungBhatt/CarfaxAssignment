package com.shrungbhatt.carfaxassignment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.shrungbhatt.carfaxassignment.adapters.CarListAdapter
import com.shrungbhatt.carfaxassignment.adapters.ICarListAdapterCallback
import com.shrungbhatt.carfaxassignment.data.models.Car
import com.shrungbhatt.carfaxassignment.data.models.Dealer
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
    private lateinit var adapter: CarListAdapter
    private var fetchJob: Job? = null
    private var snackBar: Snackbar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCarListingBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel

        setupRecyclerView()
        setupSwipeRefresh()
        subscribeToUI()
        observeEvents()
        getCars()

        return binding.root
    }

    private fun setupRecyclerView() {
        adapter = CarListAdapter (object:ICarListAdapterCallback{
            override fun onCarClick(car: Car) {
                binding.root.findNavController().navigate(
                    CarListingFragmentDirections.actionToCarDetailsFromCarList(car)
                )
            }

            override fun onCarDealerClick(dealer: Dealer) {
                TODO("Not yet implemented")
            }
        })
        binding.carList.adapter = adapter
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