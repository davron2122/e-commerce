package com.example.e_commerce.data.api.product.dto

import com.google.android.gms.analytics.ecommerce.Product


data class Section(
    val id: String,
    val products: List<Product>,
    val title: String,
    val type: SectionType
)