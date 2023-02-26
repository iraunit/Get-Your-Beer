package com.example.getyourbeer.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.getyourbeer.Popup
import com.example.getyourbeer.R
import com.example.getyourbeer.RecyclerViews.CartAdapter
import com.example.getyourbeer.RecyclerViews.FavoriteAdapter
import com.example.getyourbeer.RecyclerViews.HomeAdapter
import com.shyptsolution.getyourbeer.Database.BeerEntity
import com.shyptsolution.getyourbeer.Database.OrdersEntity
import com.shyptsolution.getyourbeer.Database.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class Favorite : Fragment(), HomeAdapter.onItemClick {
    lateinit var recyclerView:RecyclerView
    lateinit var adapter:FavoriteAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val MyView = inflater.inflate(R.layout.fragment_home, container, false)
        val dataBaseViewModel: ViewModel = ViewModelProvider(this)[ViewModel::class.java]
        recyclerView = MyView!!.findViewById<RecyclerView>(R.id.homeRecyclerView)
        recyclerView.setHasFixedSize(false)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = FavoriteAdapter(this)
        dataBaseViewModel.favBeers.observe(viewLifecycleOwner, Observer{
            adapter.setDataList(it.toMutableList())
        })
        recyclerView.adapter = adapter
        recyclerView.itemAnimator = null
        return MyView
    }

    override fun favClicked(beer: BeerEntity, position:Int) {
        val dataBaseViewModel: ViewModel = ViewModelProvider(this)[ViewModel::class.java]
        lifecycleScope.launch(Dispatchers.IO){
            dataBaseViewModel.updateFavorite(!beer.favorite,beer.id!!)
            var b=beer
            b.favorite=!beer.favorite
        }
    }

    override fun addToCart(beer:BeerEntity,position:Int) {
        val dataBaseViewModel: ViewModel = ViewModelProvider(this)[ViewModel::class.java]
        lifecycleScope.launch(Dispatchers.IO){
            dataBaseViewModel.updateCart(maxOf(0,beer.quantity!!),beer.id!!)
            var b=beer
            b.inCart=!beer.inCart
        }
    }

    override fun onbeerClicked(beerEntity: BeerEntity) {
        Popup().showPopup(requireContext(),R.layout.popup_layout,requireView(),R.id.homeRecyclerView)
    }

    override fun onOrderClicked(ordersEntity: OrdersEntity) {

    }

}