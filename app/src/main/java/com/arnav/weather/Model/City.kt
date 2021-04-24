package com.arnav.weather.Model

data class City(
    val weather:Weather,
    val main:Main,
    val wind:Wind,
    val name:String
) {
}

data class Wind(
    val speed:Float,
    val deg:Int
)

data class Weather(
    val description:String,
    val icon:String
)

data class Main(
    val temp:Double,
    val humidity:Int
)