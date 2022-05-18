package com.shrungbhatt.carfaxassignment.viewmodels

import androidx.lifecycle.ViewModel
import com.shrungbhatt.carfaxassignment.repositories.CarListingRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CarListingFragmentViewModel @Inject constructor(
    private val repository: CarListingRepository
): ViewModel(){
}