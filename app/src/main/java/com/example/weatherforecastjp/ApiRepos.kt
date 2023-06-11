package com.example.weatherforecastjp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.weatherforecastjp.MainActivity.Companion.BASE_URL

class ApiRepos {
    private fun getRetrofit(): Retrofit {

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }
    fun get(): ApiService{
        val retrofit = getRetrofit()
        return retrofit.create(ApiService::class.java)
    }
}