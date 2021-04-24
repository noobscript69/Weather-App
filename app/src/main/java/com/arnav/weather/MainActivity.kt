package com.arnav.weather

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.arnav.weather.ViewModel.WeatherViewModel
import com.arnav.weather.databinding.ActivityMainBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val weatherViewModel: WeatherViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        @OptIn
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        init()
        weatherViewModel.getCityData()
        weatherViewModel.getData.observe(this, Observer { response->

            if(response.weather.description == "clear sky" || response.weather.description == "mist"){
                Glide.with(this)
                        .load(R.drawable.clouds)
                        .into(binding.image)

            }else
                if(response.weather.description == "haze" || response.weather.description == "overcast clouds" || response.weather.description == "fog" ){
                    Glide.with(this)
                            .load(R.drawable.haze)
                            .into(binding.image)
                }else
                    if(response.weather.description == "rain"){
                        Glide.with(this)
                                .load(R.drawable.rain)
                                .into(binding.image)
                    }
            binding.description.text=response.weather.description
            binding.name.text=response.name
            binding.degree.text=response.wind.deg.toString()
            binding.speed.text=response.wind.speed.toString()
            binding.temp.text=response.main.temp.toString()
            binding.humidity.text=response.main.humidity.toString()


        })

    }

    private fun init(){

        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener,
                android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(query: String?): Boolean {
                query?.let {
                    weatherViewModel.getSearchData(it)
                }
                return true
            }

            override fun onQueryTextSubmit(newText: String?): Boolean {
                return true
            }
        })
    }
}