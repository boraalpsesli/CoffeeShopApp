package com.example.coffeeshopapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculationViewModel : ViewModel() {
    private val _totalFee = MutableLiveData<Double>()
    val totalFee: LiveData<Double> get() = _totalFee

    private val _percentTax = 0.02
    private val _delivery = 15.0

    private val _tax = MutableLiveData<Double>()
    val tax: LiveData<Double> get() = _tax

    private val _itemTotal = MutableLiveData<Double>()
    val itemTotal: LiveData<Double> get() = _itemTotal

    private val _total = MutableLiveData<Double>()
    val total: LiveData<Double> get() = _total

    fun setTotalFee(fee: Double) {
        _totalFee.value = fee
        calculateValues()
    }

    public fun calculateValues() {
        val fee = _totalFee.value ?: 0.0
        _tax.value = Math.round((fee * _percentTax) * 100) / 100.0
        _itemTotal.value = Math.round(fee * 100) / 100.0
        _total.value = Math.round((fee + (_tax.value ?: 0.0) + _delivery) * 100) / 100.0
    }
}