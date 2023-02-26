package com.example.getyourbeer.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.getyourbeer.R
import com.example.getyourbeer.RecyclerViews.CartAdapter
import com.example.getyourbeer.RecyclerViews.HomeAdapter
import com.shyptsolution.getyourbeer.Database.BeerEntity
import com.shyptsolution.getyourbeer.Database.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class Cart : Fragment(), HomeAdapter.onItemClick {
    lateinit var recyclerView:RecyclerView
    lateinit var adapter:CartAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var totalPrice=0
        val MyView = inflater.inflate(R.layout.fragment_cart, container, false)
        val dataBaseViewModel: ViewModel = ViewModelProvider(this)[ViewModel::class.java]
        recyclerView = MyView!!.findViewById<RecyclerView>(R.id.cartRecyclerView)
        recyclerView.setHasFixedSize(false)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = CartAdapter(this)
        dataBaseViewModel.cartBeers.observe(viewLifecycleOwner, Observer{
            totalPrice=0
            adapter.setDataList(it.toMutableList())
            for(i in it)totalPrice+=(i.price!! * i.quantity!!).toInt()
            MyView.findViewById<Button>(R.id.totalCost).text="Total : \u20B9"+totalPrice.toString() + ", Click to Check Out"

        })
        MyView.findViewById<Button>(R.id.totalCost).setOnClickListener {

        }
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
        }
    }

}