package com.adicoding.foodmarket.ui.home.newtaste

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adicoding.foodmarket.R
import com.adicoding.foodmarket.model.dummy.HomeVerticalModel
import com.adicoding.foodmarket.model.response.home.Data
import com.adicoding.foodmarket.utils.Helpers.formatPrice
import com.bumptech.glide.Glide

class HomeNewTasteAdapter (
    private val listData : List<Data>,
    private val itemAdapterCallback : ItemAdapterCallback
) : RecyclerView.Adapter<HomeNewTasteAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeNewTasteAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_home_vertical, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeNewTasteAdapter.ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvPrice = itemView.findViewById<TextView>(R.id.tvPrice)
        private val rbFood = itemView.findViewById<RatingBar>(R.id.rbFood)
        private val ivPoster = itemView.findViewById<ImageView>(R.id.ivPoster)

        fun bind(data: Data, itemAdapterCallback: ItemAdapterCallback) {
            tvTitle.text = data.name
            tvPrice.formatPrice(data.price.toString())
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