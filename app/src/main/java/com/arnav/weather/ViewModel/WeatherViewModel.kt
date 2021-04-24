package com.arnav.weather.ViewModel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arnav.weather.Model.City
import com.arnav.weather.Repository.WeatherRepo
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class WeatherViewModel
@ViewModelInject
constructor(private val weatherRepo: WeatherRepo) : ViewModel() {


    val getData:MutableLiveData<City> = MutableLiveData()
    @kotlinx.coroutines.ExperimentalCoroutinesApi
    val searchCity= ConflatedBroadcastChannel<String>()

    //passing the getSearchData in searchView
    @kotlinx.coroutines.ExperimentalCoroutinesApi
    fun getSearchData(city: String){
        searchCity.offer(city)
    }

@kotlinx.coroutines.FlowPreview
@OptIn
    fun getCityData() = viewModelScope.launch {

        searchCity.asFlow()
            .flatMapLatest { city->
                weatherRepo.getCityData(city)
            }.catch { e->
                Log.d("main","getCityData")
            }.collect { city ->
                getData.value=city

            }
    }
}