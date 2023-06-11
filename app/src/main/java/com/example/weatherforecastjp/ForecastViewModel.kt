package com.example.weatherforecastjp

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecastjp.MainActivity.Companion.APIKEY
import com.example.weatherforecastjp.MainActivity.Companion.LANG
import com.example.weatherforecastjp.MainActivity.Companion.UNITS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ForecastViewModel : ViewModel() {
    suspend  fun getForecast(cityName: String, city: MutableState<String>, weather: MutableList<ForecastItems>) {
        weather.clear()
        city.value = ""
        viewModelScope.launch(Dispatchers.IO) {
            val api = ApiRepos().get()
            try {
                val response = api.getWeather(cityName, UNITS, LANG, APIKEY)

                val lists = response.list
                for (listItems in lists) {
                    val dates = listItems.dt_txt
                    val temp = listItems.main.temp
                    val humidity = listItems.main.humidity
                    val pressure = listItems.main.pressure
                    val descriptionLists = listItems.weather
                    for (descriptionItem in descriptionLists) {
                        val description = descriptionItem.description
                        val icon = descriptionItem.icon

                        val iconUrl = "https://openweathermap.org/img/w/$icon.png"

                        weather.add(ForecastItems(dates, description, temp, humidity, pressure, iconUrl))

                    }
                }
                val cities = response.city.name
                city.value = cities

            } catch (e: Exception) {
                Log.d("Response　Data", "debug $e")
            }
        }
    }
}


