package com.example.e_commerce.domain.repo

import androidx.paging.PagingData
import com.example.e_commerce.data.api.product.dto.Category
import com.example.e_commerce.data.api.product.dto.Detail
import com.example.e_commerce.data.api.product.dto.HomeResponse
import com.example.e_commerce.data.api.product.dto.Product
import com.example.e_commerce.domain.model.Cart
import com.example.e_commerce.domain.model.ProductQuery

import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getHome(): HomeResponse

    suspend fun getCategories():List<Category>

    fun getProducts(query: ProductQuery) : Flow<PagingData<Product>>
    fun getRecentSearchs(): Flow<List<String>>
    suspend fun clearRecents()
    suspend fun addRecents(search:String)
    suspend fun getProduct(id:String): Detail
    suspend fun toggleWishlist(productId:String, wishlist:Boolean)

    fun getCarts(): Flow<List<Cart>>
    suspend fun clearCart()
    suspend fun setCart(cart: Cart)
}