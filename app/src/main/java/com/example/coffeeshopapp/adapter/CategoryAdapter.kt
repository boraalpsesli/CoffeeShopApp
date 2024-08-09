package com.example.coffeeshopapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.databinding.ViewholderCategoryBinding
import com.example.coffeeshopapp.model.CategoryModel

class CategoryAdapter(private val items: List<CategoryModel>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var selectedPosition = -1

    inner class ViewHolder(private val binding: ViewholderCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CategoryModel, position: Int) {
            binding.titleCat.text = item.title
            binding.root.setOnClickListener {
                val previousPosition = selectedPosition
                selectedPosition = position
                notifyItemChanged(previousPosition)
                notifyItemChanged(selectedPosition)
            }
            val backgroundResource = if (selectedPosition == position) {
                R.drawable.orange_bg
            } else {
                R.drawable.eddittext_bg
            }
            binding.titleCat.setBackgroundResource(backgroundResource)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ViewholderCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount(): Int = items.size
}
