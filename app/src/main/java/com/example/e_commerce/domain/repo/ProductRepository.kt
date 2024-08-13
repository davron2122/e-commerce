package com.example.e_commerce.domain.repo

import androidx.paging.PagingData
import com.example.e_commerce.data.api.product.dto.Category
import com.example.e_commerce.data.api.product.dto.Detail
import com.example.e_commerce.data.api.product.dto.HomeResponse
import com.example.e_commerce.domain.model.ProductQuery
import com.google.android.gms.analytics.ecommerce.Product
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

}