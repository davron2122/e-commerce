package com.example.e_commerce.domain.repo


import com.example.e_commerce.data.api.order.dto.Billing
import kotlinx.coroutines.flow.Flow

interface OrderRepository {

    fun getBilling( promo:String? = null):Flow<Billing>

    suspend fun createOrder(promo:String? = null)
}