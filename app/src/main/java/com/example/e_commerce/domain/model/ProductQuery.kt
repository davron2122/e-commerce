package com.example.e_commerce.domain.model

import android.os.Parcelable
import com.example.e_commerce.data.api.product.dto.Category
import kotlinx.parcelize.Parcelize

@Parcelize
 data class ProductQuery(
     val category :Category?=null,
     val search : String?= null,
     val rating: Int? = null,
     val discount: Int? = null,
     val sort: List<Sort> = emptyList(),
     val favorite: Boolean = false
) : Parcelable

enum class Sort{
    discount, voucher, shipping, delivery
}