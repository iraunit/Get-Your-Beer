package com.shyptsolution.codingkaro.Contests.api

import com.example.getyourbeer.API.DataClasses.BeerData


class Repository {
    suspend fun getAllBeers():List<BeerData>{
        return RetrofitInstance.api.getAllBeers()
    }


}