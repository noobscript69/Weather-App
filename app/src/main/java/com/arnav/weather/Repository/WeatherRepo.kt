package com.arnav.weather.Repository

import com.arnav.weather.Model.City
import com.arnav.weather.Network.ApiServiceUse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

//This class is for performing all habitus operations

class WeatherRepo @Inject constructor(private val  apiServiceUse: ApiServiceUse) {

    //Flow will fetch data from server Asynchronizely and emit one by one

    fun getCityData(city:String): Flow<City> = flow {
        val response = apiServiceUse.getCityData(city,"2089a9939891ed7de643fa7beb96dd6d" )
        emit(response)
    }.flowOn(Dispatchers.IO)
        //conflate let the recent search proceed
        .conflate()

}