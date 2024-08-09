package com.example.coffeeshopapp.scenes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.coffeeshopapp.BaseFragment
import com.example.coffeeshopapp.adapter.SizeAdapter
import com.example.coffeeshopapp.databinding.FragmentDetailsBinding
import com.example.coffeeshopapp.helper.ManagmentCart
import com.example.coffeeshopapp.model.ItemsModel
import com.example.coffeeshopapp.viewmodel.MainViewModel

class DetailsFragment : BaseFragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var item: ItemsModel
    private lateinit var managmentCart: ManagmentCart
    private val viewModel = MainViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        item = arguments?.getSerializable("object") as? ItemsModel ?: ItemsModel()

        managmentCart =
            ManagmentCart(requireContext()) // Replace with actual initialization

        setupUI()
        initSizeList()
    }

    private fun initSizeList() {
        val sizeList = listOf("1", "2", "3", "4")

        binding.sizeList.adapter = SizeAdapter(sizeList)
        binding.sizeList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val colorList = item.picUrl
        if (colorList.isNotEmpty()) {
            Glide.with(this)
                .load(colorList[0])
                .apply(RequestOptions.bitmapTransform(RoundedCorners(100)))
                .into(binding.picMain)
        }
    }

    private fun setupUI() {
        binding.apply {
            titleTxt.text = item.title
            descriptionTxt.text = item.description
            priceTxt.text = "${item.price}"
            ratingBar.rating = item.rating.toFloat()

            addToCartBtn.setOnClickListener {
                item.numberInCart = numberItemTxt.text.toString().toInt()
                managmentCart.insertItems(item)
            }

            backBtn.setOnClickListener { findNavController().navigate(MainFragmentDirections.backToMain()) }

            plusCart.setOnClickListener {
                item.numberInCart++
                numberItemTxt.text = item.numberInCart.toString()
            }

            minusCart.setOnClickListener {
                if (item.numberInCart > 0) {
                    item.numberInCart--
                    numberItemTxt.text = item.numberInCart.toString()
                }
            }

            viewModel.fetchFavoriteStatus(item.title)
            viewModel.itemFavStatus.observe(viewLifecycleOwner, Observer { isFavourite ->
                favBtn.setImageDrawable(viewModel.getFavDrawable(requireContext(), isFavourite))
            })

            favBtn.setOnClickListener {
                val currentStatus = viewModel.itemFavStatus.value ?: false
                val newStatus = !currentStatus
                viewModel.addFav(item.title, newStatus)
            }

        }


    }

    override fun onResume() {
        super.onResume()
        // Re-fetch or update item details when fragment is resumed
        binding.apply {
            val updatedDrawable = viewModel.getFavDrawable(requireContext(), item.isFav)
            favBtn.setImageDrawable(updatedDrawable)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = DetailsFragment()
    }
}
