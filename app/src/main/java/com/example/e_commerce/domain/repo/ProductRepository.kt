package com.example.e_commerce.domain.repo

interface ProductRepository {

    suspend fun getHoome(): HomeRes
}