package com.example.coffeeshopapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.databinding.ViewholderCategoryBinding
import com.example.coffeeshopapp.model.CategoryModel

class CategoryAdapter (val items:MutableList<CategoryModel>):RecyclerView.Adapter<CategoryAdapter.ViewHolder>(){
    private lateinit var context:Context
    private var selectedPosition=-1
    private var lastSelectedPosition=-1
    inner class ViewHolder(val binding:ViewholderCategoryBinding):RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder {
        context=parent.context
        val binding=ViewholderCategoryBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        val item=items[position]
        holder.binding.titleCat.text=item.title
        holder.binding.root.setOnClickListener{
            lastSelectedPosition=selectedPosition
            selectedPosition=position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }
        if(selectedPosition==position) {
            holder.binding.titleCat.setBackgroundResource(R.drawable.orange_bg)
        }else{
            holder.binding.titleCat.setBackgroundResource(R.drawable.eddittext_bg)
        }
        }


    override fun getItemCount(): Int =items.size
}