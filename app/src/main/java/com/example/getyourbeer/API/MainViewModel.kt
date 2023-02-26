package com.shyptsolution.codingkaro.Contests.api


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.getyourbeer.API.DataClasses.BeerData
import kotlinx.coroutines.launch

class MainViewModel (private val repository: Repository): androidx.lifecycle.ViewModel() {

    val myResponse: MutableLiveData<List<BeerData>> = MutableLiveData()

    fun getAllBeers(){
        viewModelScope.launch {
            val response:List<BeerData> = repository.getAllBeers()
            myResponse.value=response
        }
    }


}