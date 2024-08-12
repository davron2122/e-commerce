package com.example.e_commerce.domain.repo

import com.example.e_commerce.data.api.product.dto.HomeResponse

interface ProductRepository {

    suspend fun getHome(): HomeResponse
    suspend fun
}