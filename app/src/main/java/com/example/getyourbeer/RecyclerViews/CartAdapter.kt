package com.example.getyourbeer.RecyclerViews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.getyourbeer.R
import com.shyptsolution.getyourbeer.Database.BeerEntity
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

class CartAdapter(val listener:HomeAdapter.onItemClick): ListAdapter<BeerEntity, CartAdapter.ViewHolder>(SleepNightDiffCallback()) {
    private var dataList: MutableList<BeerEntity> = ArrayList()
    fun setDataList(listOfData: MutableList<BeerEntity>) {
        dataList = listOfData
        submitList(listOfData)
    }
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.beer_name)
        val tagline = itemView.findViewById<TextView>(R.id.tagline)
        val attenuationLevel = itemView.findViewById<TextView>(R.id.attenuation_level)
        val price = itemView.findViewById<TextView>(R.id.price)
        val beerPic = itemView.findViewById<ImageView>(R.id.beer_pic)
        val addToFav = itemView.findViewById<ImageView>(R.id.addTofav)
        val addToCart = itemView.findViewById<Button>(R.id.addToCart)
        val plus = itemView.findViewById<ImageView>(R.id.plus)
        val minus = itemView.findViewById<ImageView>(R.id.minus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.home_row_layout, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val df = DecimalFormat("#.##")
        val current=dataList[position]
        holder.name.text=current.name
        holder.tagline.text=current.tagline
        holder.attenuationLevel.text="Attenuation Level: "+(df.format(current.attenuationLevel)).toString()+'%'
        holder.price.text="\u20B9" +(df.format(current.price)).toString()
        Picasso.get().load(current.imageUrl).into(holder.beerPic)
        if(current.favorite) holder.addToFav.setImageResource(R.drawable.heart_filled)
        else holder.addToFav.setImageResource(R.drawable.heart_outlined)

        if(current.quantity!! >0) holder.addToCart.text=current.quantity.toString()
        else holder.addToCart.text= "Add To Cart"

        holder.addToFav.setOnClickListener {
            if(!current.favorite) holder.addToFav.setImageResource(R.drawable.heart_filled)
            else holder.addToFav.setImageResource(R.drawable.heart_outlined)
            listener.favClicked(current,position)
        }

        holder.addToCart.setOnClickListener {
            if(current.quantity==0){
                holder.plus.performClick()
            }
        }

        holder.plus.setOnClickListener { current.quantity = current.quantity?.plus(+1); holder.addToCart.text=current.quantity.toString(); listener.addToCart(current,position);}

        holder.minus.setOnClickListener { current.quantity = current.quantity?.minus(+1);  if (current.quantity!! ==0){holder.addToCart.text="Add to Cart"} else if(current.quantity!! >=0) holder.addToCart.text=current.quantity.toString(); listener.addToCart(current,position);}

        holder.addToFav.visibility=View.GONE
        holder.attenuationLevel.visibility=View.GONE
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}