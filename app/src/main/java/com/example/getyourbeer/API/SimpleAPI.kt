package com.shyptsolution.codingkaro.Contests.api

import com.example.getyourbeer.API.DataClasses.BeerData
import retrofit2.http.GET

interface SimpleAPI {

    @GET("beers")
    suspend fun getAllBeers (): List<BeerData>

}