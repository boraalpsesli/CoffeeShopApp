package com.example.coffeeshopapp.scenes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeeshopapp.adapter.CartAdapter
import com.example.coffeeshopapp.databinding.FragmentCartBinding
import com.example.coffeeshopapp.helper.ChangeNumberItemsListener
import com.example.coffeeshopapp.helper.ManagmentCart
import com.example.coffeeshopapp.viewmodel.CalculationViewModel

class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private val managment: ManagmentCart by lazy { ManagmentCart(requireContext()) }
    private val calculationViewModel: CalculationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCartList()
        setupViewModel()
        binding.backBtn.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.backToMain())
        }
    }

    private fun initCartList() {
        with(binding) {
            cartView.layoutManager = LinearLayoutManager(requireContext())
            val adapter = CartAdapter(
                managment.getListCart(),
                requireContext(),
                object : ChangeNumberItemsListener {
                    override fun onChanged() {
                        // Update the ViewModel when the cart changes
                        calculationViewModel.setTotalFee(managment.getTotalFee())
                    }
                }
            )
            cartView.adapter = adapter
        }
    }

    private fun setupViewModel() {
        calculationViewModel.tax.observe(viewLifecycleOwner) { tax ->
            binding.taxTxt.text = "$${tax}"
        }

        calculationViewModel.itemTotal.observe(viewLifecycleOwner) { itemTotal ->
            binding.totalFeeTxt.text = "$${itemTotal}"
        }

        calculationViewModel.total.observe(viewLifecycleOwner) { total ->
            binding.totalTxt.text = "$${total}"
        }

        // Initialize ViewModel with the total fee
        calculationViewModel.setTotalFee(managment.getTotalFee())
    }

    companion object {
        @JvmStatic
        fun newInstance() = CartFragment()
    }
}
