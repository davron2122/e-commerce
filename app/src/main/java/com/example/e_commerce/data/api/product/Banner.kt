package com.example.e_commerce.data.api.product

import com.google.android.gms.analytics.ecommerce.Product
import java.util.Locale.Category

data class Banner(

    val category: Category,
    val image: String,
    val product: Product

)
