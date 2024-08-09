package com.example.coffeeshopapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.databinding.ViewholderSizeBinding

class SizeAdapter(private val items: List<String>) :
    RecyclerView.Adapter<SizeAdapter.ViewHolder>() {

    private var selectedPosition = -1

    inner class ViewHolder(private val binding: ViewholderSizeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int, context: Context) {
            val sizeInDp = when (position) {
                0 -> 45
                1 -> 50
                2 -> 55
                3 -> 65
                else -> 70
            }

            val imageSize = sizeInDp.dpToPx(context)
            binding.img.layoutParams = binding.img.layoutParams.apply {
                width = imageSize
                height = imageSize
            }

            binding.root.setOnClickListener {
                val previousPosition = selectedPosition
                selectedPosition = position
                notifyItemChanged(previousPosition)
                notifyItemChanged(selectedPosition)
            }

            val backgroundResource = if (selectedPosition == position) {
                R.drawable.orange_bg
            } else {
                R.drawable.size_bg
            }
            binding.img.setBackgroundResource(backgroundResource)
        }

        private fun Int.dpToPx(context: Context): Int {
            return (this * context.resources.displayMetrics.density).toInt()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ViewholderSizeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position, holder.itemView.context)
    }

    override fun getItemCount(): Int = items.size
}
