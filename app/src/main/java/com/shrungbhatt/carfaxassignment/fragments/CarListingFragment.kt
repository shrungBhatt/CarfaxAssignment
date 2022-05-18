package com.shrungbhatt.carfaxassignment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.shrungbhatt.carfaxassignment.databinding.FragmentCarListingBinding
import com.shrungbhatt.carfaxassignment.viewmodels.CarListingFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CarListingFragment: Fragment() {

    private lateinit var binding: FragmentCarListingBinding
    private val viewModel: CarListingFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCarListingBinding.inflate(inflater, container, false)

        return binding.root
    }

}