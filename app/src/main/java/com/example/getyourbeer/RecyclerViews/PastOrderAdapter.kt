package com.example.getyourbeer.RecyclerViews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.getyourbeer.R
import com.shyptsolution.getyourbeer.Database.OrdersEntity

class PastOrderAdapter(val listener:HomeAdapter.onItemClick): ListAdapter < OrdersEntity, PastOrderAdapter.ViewHolder>(SleepNightDiffCallback()) {

    private var dataList: MutableList<OrdersEntity> = ArrayList()
    fun setDataList(listOfData: MutableList<OrdersEntity>) {
        dataList = listOfData
        submitList(listOfData)
    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.home_row_layout, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}