package com.example.coffeeshopapp.model

import java.io.Serializable

data class ItemsModel(
    var isFav: Boolean = false,
    var title: String = "",
    var description: String = "",
    var picUrl: ArrayList<String> = ArrayList(),
    val price: Double = 0.0,
    var rating: Double = 0.0,
    var numberInCart: Int = 0,
    var extra: String = ""

) : Serializable