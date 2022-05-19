package com.shrungbhatt.carfaxassignment.viewmodels

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shrungbhatt.carfaxassignment.data.models.Car
import com.shrungbhatt.carfaxassignment.repositories.CarListingRepository
import com.shrungbhatt.carfaxassignment.util.EventType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarListingFragmentViewModel @Inject constructor(
    private val repository: CarListingRepository
) : ViewModel() {

    val cars: MutableLiveData<List<Car>> by lazy {
        MutableLiveData<List<Car>>()
    }

    val errors: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val isRefreshing = ObservableBoolean()
    val noData = ObservableBoolean(true)

    fun fetchCars() {
        viewModelScope.launch {
            startedLoading()
            observeResponse()
            repository.fetchCars()
        }
    }

    private fun observeResponse() {
        viewModelScope.launch {
            repository.latestData
                .collect {
                    receivedCars(it)
                    finishedLoading()
                }
        }
    }

    private fun startedLoading() {
        isRefreshing.set(true)
    }

    private fun finishedLoading() {
        isRefreshing.set(false)
    }

    fun observeEvents() {
        viewModelScope.launch {
            repository.eventChannel.eventFlow.collect {
                finishedLoading()
                errors.value = it.message
            }
        }
    }

    private fun receivedCars(cars: List<Car>) {
        if (cars.isEmpty()) {
            noData.set(true)
        } else {
            noData.set(false)
        }
        this.cars.value = cars
    }

}