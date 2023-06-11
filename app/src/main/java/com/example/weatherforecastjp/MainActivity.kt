package com.example.weatherforecastjp


import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModelProvider
import com.example.weatherforecastjp.ui.theme.WeatherForecastJpTheme



class MainActivity : ComponentActivity() {

    companion object{
        const val BASE_URL = "https://api.openweathermap.org"
        const val UNITS = "metric"
        const val LANG = "ja"

        const val APIKEY = BuildConfig.API_KEY
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel = ViewModelProvider(this)[ForecastViewModel::class.java]
        super.onCreate(savedInstanceState)
        setContent {
            WeatherForecastJpTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                        mainScreen(viewModel)
                }
            }
        }
    }
}
