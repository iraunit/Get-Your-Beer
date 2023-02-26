package com.shyptsolution.getyourbeer.Database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(application: Application):AndroidViewModel(application) {
    var repository:Repository
    var allBeers:LiveData<List<BeerEntity>>
    var favBeers:LiveData<List<BeerEntity>>
    var cartBeers:LiveData<List<BeerEntity>>
    var pastOrder:LiveData<List<OrdersEntity>>
    init {
        val myDao=Database(application.applicationContext).getDao()
        repository= Repository(myDao)
        allBeers=repository.allBeers
        favBeers=repository.favBeers
        cartBeers=repository.cartBeers
        pastOrder=repository.pastOrder
    }

    fun insertBeer(beerEntity: BeerEntity)=viewModelScope.launch (Dispatchers.IO){
        repository.insertBeer(beerEntity)
    }

    fun insertOrder(ordersEntity: OrdersEntity)=viewModelScope.launch (Dispatchers.IO){
        repository.insertOrder(ordersEntity)
    }

    fun updateCart(cart:Int,id:Int)=viewModelScope.launch(Dispatchers.IO){
        repository.updateCart(cart,id)
    }

    fun updateFavorite(fav:Boolean,id:Int)=viewModelScope.launch(Dispatchers.IO){
        repository.updateFavorite(fav,id)
    }


}