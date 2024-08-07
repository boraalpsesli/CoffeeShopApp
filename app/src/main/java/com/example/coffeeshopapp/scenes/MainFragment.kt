package com.example.coffeeshopapp.scenes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeeshopapp.BaseFragment
import com.example.coffeeshopapp.adapter.CategoryAdapter
import com.example.coffeeshopapp.adapter.OfferAdapter
import com.example.coffeeshopapp.adapter.PopularAdapter
import com.example.coffeeshopapp.databinding.FragmentMainBinding
import com.example.coffeeshopapp.viewmodel.MainViewModel


class MainFragment : BaseFragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel = MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCategory()
        initPopular()
        initOffers()
    }

    private fun initCategory() {
        binding.progressBarCategory.visibility = View.VISIBLE
        viewModel.category.observe(viewLifecycleOwner, Observer {
            binding.recyclerViewCategory.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerViewCategory.adapter = CategoryAdapter(it)
            binding.progressBarCategory.visibility = View.GONE
        })
        viewModel.loadCategory()
    }

    private fun initPopular() {
        binding.progressBarPopular.visibility = View.VISIBLE
        viewModel.popular.observe(viewLifecycleOwner, Observer {
            binding.recyclerViewPopular.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerViewPopular.adapter = PopularAdapter(it)
            binding.progressBarPopular.visibility = View.GONE
        })
        viewModel.loadPopular()
    }

    private fun initOffers() {
        binding.progressBarOffer.visibility = View.VISIBLE
        viewModel.offer.observe(viewLifecycleOwner, Observer {
            binding.recyclerViewOffer.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerViewOffer.adapter = OfferAdapter(it)
            binding.progressBarOffer.visibility = View.GONE
        })
        viewModel.loadOffer()
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            MainFragment()

    }
}