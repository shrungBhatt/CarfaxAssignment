package com.shrungbhatt.carfaxassignment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.shrungbhatt.carfaxassignment.databinding.FragmentCarDetailsBinding
import com.shrungbhatt.carfaxassignment.viewmodels.CarDetailsFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CarDetailsFragment: Fragment() {

    private lateinit var binding: FragmentCarDetailsBinding
    private val viewModel: CarDetailsFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCarDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

}