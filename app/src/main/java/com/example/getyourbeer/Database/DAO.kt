package com.shyptsolution.getyourbeer.Database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface DAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBeer(beerEntity: BeerEntity)

    @Query("SELECT * FROM beer_details ORDER BY name ASC")
    fun getAllBeers(): LiveData<List<BeerEntity>>

    @Query("SELECT * FROM beer_details WHERE favorite=1")
    fun getFavBeers(): LiveData<List<BeerEntity>>

    @Query("SELECT * FROM beer_details WHERE quantity>0")
    fun getCartBeers(): LiveData<List<BeerEntity>>

    @Query("UPDATE beer_details SET favorite=:fav  WHERE id=:id")
    suspend fun updateFavorite(fav:Boolean,id:Int)

    @Query("UPDATE beer_details SET quantity=:cart  WHERE id=:id")
    suspend fun updateCart(cart:Int,id:Int)

    @Query("UPDATE beer_details SET quantity=:quantity  WHERE id=:id")
    suspend fun updateQuantity(quantity:Int,id:Int)
}