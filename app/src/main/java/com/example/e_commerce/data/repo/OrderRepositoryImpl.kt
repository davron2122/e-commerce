package com.example.e_commerce.data.repo


import com.example.e_commerce.data.api.order.OrderApi
import com.example.e_commerce.data.api.order.dto.CartDto
import com.example.e_commerce.data.api.order.dto.CartRequest
import com.example.e_commerce.data.store.CartStore
import com.example.e_commerce.domain.repo.OrderRepository
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private  val orderApi: OrderApi,
    private val cartStore: CartStore
): OrderRepository {
    override fun getBilling(promo: String?) = channelFlow {
        cartStore.getFlow().distinctUntilChanged().collectLatest { carts ->
            carts ?: return@collectLatest
            val request = CartRequest(
                cart = carts.map { CartDto(id = it.id, count = it.count) },
                promoCode = promo
            )
            val billing = orderApi.getBilling(request)
            send(billing)
        }
    }

    override suspend fun createOrder(promo: String?) {
        val carts = cartStore.get() ?: return
        val request = CartRequest(
            cart = carts.map { CartDto(id = it.id, count = it.count) },
            promoCode = promo
        )
        orderApi.createOrder(request)
        cartStore.clear()
    }

}