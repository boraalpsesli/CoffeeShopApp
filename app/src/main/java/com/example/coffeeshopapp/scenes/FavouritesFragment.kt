package com.example.coffeeshopapp.scenes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeeshopapp.BaseFragment
import com.example.coffeeshopapp.adapter.FavAdapter
import com.example.coffeeshopapp.databinding.FragmentFavouritesBinding
import com.example.coffeeshopapp.viewmodel.MainViewModel


class FavouritesFragment : BaseFragment() {
    private lateinit var binding: FragmentFavouritesBinding
    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private fun initFav() {
        binding.progressBarFav.visibility = View.VISIBLE
        viewModel.favourites.observe(viewLifecycleOwner, Observer { favourites ->
            binding.favView.layoutManager =
                LinearLayoutManager(requireContext())
            binding.favView.adapter = FavAdapter(favourites)
            binding.progressBarFav.visibility = View.GONE


        })
        viewModel.loadFavourites()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavouritesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFav()
        binding.backBtn.setOnClickListener { findNavController().navigate(MainFragmentDirections.toMainFragment()) }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            FavouritesFragment()

    }
}