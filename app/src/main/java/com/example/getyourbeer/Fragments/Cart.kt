package com.example.getyourbeer.Fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.RequiresApi
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
import com.shyptsolution.getyourbeer.Database.OrdersEntity
import com.shyptsolution.getyourbeer.Database.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*


class Cart : Fragment(), HomeAdapter.onItemClick {
    lateinit var recyclerView:RecyclerView
    lateinit var adapter:CartAdapter
    @RequiresApi(Build.VERSION_CODES.O)
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
            if(totalPrice==0)return@setOnClickListener
            val items= arrayListOf<String>()
            val cost= arrayListOf<String>()
            val quantity= arrayListOf<String>()
            for(it in adapter.currentList){
                items.add(it.name!!)
                cost.add(it.price?.toInt().toString())
                quantity.add(it.quantity?.toInt().toString())
            }
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
            val formatted = current.format(formatter)
            val order=OrdersEntity(formatted,totalPrice,items,cost,quantity)
            lifecycleScope.launch(Dispatchers.IO){
                dataBaseViewModel.insertOrder(order)
                for(it in adapter.currentList){
                    dataBaseViewModel.updateCart(0,it.id!!)
                }
            }
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