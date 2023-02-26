package com.shyptsolution.getyourbeer.Database

import androidx.lifecycle.LiveData
import com.shyptsolution.getyourbeer.Database.BeerEntity
import com.shyptsolution.getyourbeer.Database.DAO
import kotlinx.coroutines.flow.Flow

class Repository(private val myDao: DAO) {
    val allBeers:LiveData<List<BeerEntity>> = myDao.getAllBeers()
    val favBeers:LiveData<List<BeerEntity>> = myDao.getFavBeers()
    val cartBeers:LiveData<List<BeerEntity>> = myDao.getCartBeers()

    suspend fun insertBeer(beerEntity: BeerEntity){
        myDao.insertBeer(beerEntity)
    }

    suspend fun updateCart(cart:Int,id:Int){
        myDao.updateCart(cart,id)
    }

    suspend fun updateFavorite(fav:Boolean,id:Int){
        myDao.updateFavorite(fav,id)
    }

}