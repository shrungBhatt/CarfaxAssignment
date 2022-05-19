package com.shrungbhatt.carfaxassignment.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.shrungbhatt.carfaxassignment.databinding.FragmentCarDetailsBinding
import com.shrungbhatt.carfaxassignment.viewmodels.CarDetailsFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CarDetailsFragment: Fragment() {

    private lateinit var binding: FragmentCarDetailsBinding
    private val viewModel: CarDetailsFragmentViewModel by viewModels()
    private val carListingArgs: CarDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCarDetailsBinding.inflate(inflater, container, false)
        binding.apply {
            car = carListingArgs.car
        }
        binding.callDealer.setOnClickListener {
            val uri = "tel:" + binding.car?.dealerPhoneNumber
            val intent = Intent(Intent.ACTION_DIAL).also { it.data = Uri.parse(uri) }
            startActivity(intent)
        }
        return binding.root
    }

}