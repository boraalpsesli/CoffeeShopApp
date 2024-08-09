package com.example.coffeeshopapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.coffeeshopapp.databinding.ViewholderCartBinding
import com.example.coffeeshopapp.helper.ChangeNumberItemsListener
import com.example.coffeeshopapp.helper.ManagmentCart
import com.example.coffeeshopapp.model.ItemsModel

class CartAdapter(
    private val listItemSelected: ArrayList<ItemsModel>,
    context: Context,
    var changeNumberItemsListener: ChangeNumberItemsListener? = null
) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    private val managmentCart = ManagmentCart(context)

    inner class ViewHolder(val binding: ViewholderCartBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemsModel) {
            with(binding) {
                titleTxt.text = item.title
                feeEachItem.text = "$${item.price}"
                totalEachItem.text = "$${Math.round(item.numberInCart * item.price)}"
                numberItemTxt.text = item.numberInCart.toString()

                Glide.with(itemView.context)
                    .load(item.picUrl[0])
                    .apply(RequestOptions().transform(CenterCrop()))
                    .into(picCart)

                plusCartBtn.setOnClickListener {
                    updateItemCount(item, true)
                }

                minusCartBtn.setOnClickListener {
                    updateItemCount(item, false)
                }
            }
        }

        private fun updateItemCount(item: ItemsModel, isAdding: Boolean) {
            if (isAdding) {
                managmentCart.plusItem(
                    listItemSelected,
                    adapterPosition,
                    object : ChangeNumberItemsListener {
                        override fun onChanged() {
                            notifyItemChanged(adapterPosition)
                            notifyDataSetChanged()

                            changeNumberItemsListener?.onChanged()
                        }
                    })
            } else {
                managmentCart.minusItem(
                    listItemSelected,
                    adapterPosition,
                    object : ChangeNumberItemsListener {
                        override fun onChanged() {
                            notifyItemChanged(adapterPosition)
                            notifyDataSetChanged()
                            changeNumberItemsListener?.onChanged()
                        }
                    })
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ViewholderCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listItemSelected[position])
    }

    override fun getItemCount(): Int = listItemSelected.size
}
