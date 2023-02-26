package com.example.getyourbeer.RecyclerViews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.getyourbeer.R
import com.shyptsolution.getyourbeer.Database.OrdersEntity
import org.w3c.dom.Text

class OrderAdapter(val listener:HomeAdapter.onItemClick):  RecyclerView.Adapter<OrderAdapter.ViewHolder>() {
    private var dataList: List<OrdersEntity> = ArrayList()
    fun setDataList(listOfData: List<OrdersEntity>) {
        dataList = listOfData
    }
    class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
        val price=itemView.findViewById<TextView>(R.id.price)
        val dateTime=itemView.findViewById<TextView>(R.id.tagline)
        val allItems=itemView.findViewById<TextView>(R.id.beer_name)
        val totalItems=itemView.findViewById<TextView>(R.id.attenuation_level)
        val liner=itemView.findViewById<LinearLayout>(R.id.buttonLayout)
        val fav = itemView.findViewById<ImageView>(R.id.addTofav)
        val atten=itemView.findViewById<TextView>(R.id.attenuation_level)
        val card = itemView.findViewById<CardView>(R.id.cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.home_row_layout, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current=dataList[position]
        var totalPrice = 0
        for(i in current.price)totalPrice+=i.toInt()
        holder.price.text="₹ "+totalPrice.toString()
        holder.dateTime.text= "Order Date & Time: "+current.orderDateTime
        var str = "Items Ordered: "
        for(i in current.items)str  = "$str$i, "
        holder.allItems.text=str
        holder.liner.visibility=View.GONE
        holder.fav.visibility=View.GONE
        holder.atten.visibility=View.GONE
        holder.card.setOnClickListener { listener.onOrderClicked(current) }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}