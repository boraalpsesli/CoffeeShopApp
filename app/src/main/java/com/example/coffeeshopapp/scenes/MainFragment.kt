package com.example.coffeeshopapp.scenes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeeshopapp.BaseFragment
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.adapter.CategoryAdapter
import com.example.coffeeshopapp.databinding.FragmentIntroBinding
import com.example.coffeeshopapp.databinding.FragmentMainBinding
import com.example.coffeeshopapp.viewmodel.MainViewModel


class MainFragment : BaseFragment() {
   private lateinit var binding: FragmentMainBinding
   private val viewModel=MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
       binding=FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCategory()
    }
    private fun initCategory(){
        binding.progressBarCategory.visibility=View.VISIBLE
        viewModel.category.observe(viewLifecycleOwner, Observer {
            binding.recyclerViewCategory.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            binding.recyclerViewCategory.adapter=CategoryAdapter(it)
            binding.progressBarCategory.visibility=View.GONE
        })
        viewModel.loadCategory()
    }
    companion object {

        @JvmStatic
        fun newInstance() =
            MainFragment()

    }
}