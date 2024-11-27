package com.adicoding.foodmarket.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adicoding.foodmarket.R
import com.adicoding.foodmarket.model.dummy.HomeModel
import com.adicoding.foodmarket.model.response.home.Data
import com.bumptech.glide.Glide

class HomeAdapter (
    private val listData : List<Data>,
    private val itemAdapterCallback : ItemAdapterCallback
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_home_horizontal, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val rbFood = itemView.findViewById<RatingBar>(R.id.rbFood)
        private val ivPoster = itemView.findViewById<ImageView>(R.id.ivPoster)

        fun bind(data: Data, itemAdapterCallback: ItemAdapterCallback) {
            tvTitle.text = data.name
            rbFood.rating = data.rate?.toFloat() ?: 0f

            Glide.with(itemView.context)
                .load(data.picturePath)
                .into(ivPoster)

            itemView.setOnClickListener { itemAdapterCallback.onClick(it, data) }
        }
    }


    interface ItemAdapterCallback {
        fun onClick(v: View, data: Data)

    }
}