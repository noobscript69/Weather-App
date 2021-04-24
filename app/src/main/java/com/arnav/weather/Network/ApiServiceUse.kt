package com.arnav.weather.Network

import com.arnav.weather.Model.City
import javax.inject.Inject

class ApiServiceUse @Inject constructor(private val apiService: ApiService){

    suspend fun getCityData(city:String, appId:String): City =
        apiService.getCityData(city,appId)

}