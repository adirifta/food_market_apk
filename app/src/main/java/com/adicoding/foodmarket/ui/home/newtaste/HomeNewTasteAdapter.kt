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
import com.adicoding.foodmarket.utils.Helpers.formatPrice

class HomeNewTasteAdapter (
    private val listData : List<HomeVerticalModel>,
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

        fun bind(data: HomeVerticalModel, itemAdapterCallback: ItemAdapterCallback) {
            tvTitle.text = data.title
            tvPrice.formatPrice(data.price)
            rbFood.rating = data.rating

//            Glide.with(itemView.context)
//                .load(data.src)
//                .into(ivPoster)

            itemView.setOnClickListener { itemAdapterCallback.onClick(it, data) }
        }
    }


    interface ItemAdapterCallback {
        fun onClick(v: View, data: HomeVerticalModel)

    }
}