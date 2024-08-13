package com.example.e_commerce.data.api.product.paging

import com.example.e_commerce.data.api.product.ProductApi
import com.example.e_commerce.domain.model.ProductQuery

class ProductPagingSource (

    private val productApi: ProductApi,
    private val query: ProductQuery

)
