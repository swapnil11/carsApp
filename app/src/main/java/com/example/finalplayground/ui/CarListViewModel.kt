package com.example.finalplayground.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.example.finalplayground.data.network.Resource
import com.example.finalplayground.domain.usecases.CarUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CarListViewModel @Inject constructor(private val useCase: CarUseCase) : ViewModel() {

    private val remoteCarsLiveData = MutableLiveData(Unit)

    var remoteCars = remoteCarsLiveData.switchMap {
        fetchCars()
    }

    private fun fetchCars() = liveData {
        emit(Resource.loading())
        emit(useCase.cars())
    }
}
