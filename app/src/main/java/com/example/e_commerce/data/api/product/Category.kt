package com.example.e_commerce.data.api.product

import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(


    val count : Int,
    val id : String,
    val image : String,
    val title: String
):Parcelize
