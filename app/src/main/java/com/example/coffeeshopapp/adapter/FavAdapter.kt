package com.example.coffeeshopapp.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.databinding.ViewholderFavouritesBinding
import com.example.coffeeshopapp.model.ItemsModel

class FavAdapter(private val items: List<ItemsModel>) :
    RecyclerView.Adapter<FavAdapter.ViewHolder>() {
    class ViewHolder(val binding: ViewholderFavouritesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ItemsModel) {
            binding.apply {
                titleTxt.text = item.title
                priceTxt.text = "$${item.price}"
                ratingBar.rating = item.rating.toFloat()
                extraTxt.text = item.extra

                Glide.with(itemView.context)
                    .load(item.picUrl[0])
                    .into(pic)

                itemView.setOnClickListener {
                    val bundle = Bundle().apply {
                        putSerializable("object", item)
                    }
                    itemView.findNavController().navigate(R.id.toDetails, bundle)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ViewholderFavouritesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}

