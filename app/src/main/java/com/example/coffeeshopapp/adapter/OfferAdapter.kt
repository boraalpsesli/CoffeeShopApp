package com.example.coffeeshopapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffeeshopapp.databinding.ViewholderOfferBinding
import com.example.coffeeshopapp.model.ItemsModel

class OfferAdapter(private val items: List<ItemsModel>) :
    RecyclerView.Adapter<OfferAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ViewholderOfferBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ItemsModel) {
            binding.apply {
                titleTxt.text = item.title
                priceTxt.text = "$${item.price}"

                Glide.with(itemView.context)
                    .load(item.picUrl[0])
                    .into(pic)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ViewholderOfferBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
