package com.shrungbhatt.carfaxassignment.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shrungbhatt.carfaxassignment.data.models.Car
import com.shrungbhatt.carfaxassignment.repositories.CarListingRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarListingFragmentViewModel @Inject constructor(
    private val repository: CarListingRepository
): ViewModel(){

    val cars: MutableLiveData<List<Car>> by lazy {
        MutableLiveData<List<Car>>()
    }

    fun fetchCars() {
        viewModelScope.launch {
            repository.latestData
                .catch {

                }
                .collect{
                cars.value = it
            }
        }
    }

}