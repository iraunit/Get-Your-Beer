package com.example.getyourbeer.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.getyourbeer.Popup
import com.example.getyourbeer.R
import com.example.getyourbeer.RecyclerViews.FavoriteAdapter
import com.example.getyourbeer.RecyclerViews.HomeAdapter
import com.example.getyourbeer.RecyclerViews.OrderAdapter
import com.shyptsolution.getyourbeer.Database.BeerEntity
import com.shyptsolution.getyourbeer.Database.OrdersEntity
import com.shyptsolution.getyourbeer.Database.ViewModel

class PastOrder : Fragment(), HomeAdapter.onItemClick {
    lateinit var recyclerView:RecyclerView
    lateinit var adapter:OrderAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val MyView = inflater.inflate(R.layout.fragment_past_order, container, false)
        val dataBaseViewModel: ViewModel = ViewModelProvider(this)[ViewModel::class.java]
        recyclerView = MyView!!.findViewById<RecyclerView>(R.id.pastOrderRecyclerView)
        recyclerView.setHasFixedSize(false)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = OrderAdapter(this)
        dataBaseViewModel.pastOrder.observe(viewLifecycleOwner, Observer{
            adapter.setDataList(it)
            adapter.notifyDataSetChanged()
        })
        recyclerView.adapter = adapter
        recyclerView.itemAnimator = null

        return MyView
    }

    override fun favClicked(beerEntity: BeerEntity, position: Int) {

    }

    override fun addToCart(beerEntity: BeerEntity, position: Int) {

    }

    override fun onbeerClicked(beerEntity: BeerEntity) {

    }

    override fun onOrderClicked(ordersEntity: OrdersEntity) {
        Popup().showPopup(requireContext(),R.layout.popup_layout,requireView(),R.id.pastOrderRecyclerView)
    }

}