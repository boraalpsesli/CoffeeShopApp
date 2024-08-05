package com.example.coffeeshopapp.scenes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coffeeshopapp.BaseFragment
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.databinding.FragmentIntroBinding
import com.example.coffeeshopapp.databinding.FragmentMainBinding


class MainFragment : BaseFragment() {
   private lateinit var binding: FragmentMainBinding
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

    companion object {

        @JvmStatic
        fun newInstance() =
            MainFragment()

    }
}