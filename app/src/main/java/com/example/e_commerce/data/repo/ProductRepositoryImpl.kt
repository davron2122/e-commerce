package com.example.e_commerce.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.e_commerce.data.api.product.ProductApi
import com.example.e_commerce.data.api.product.dto.HomeResponse
import com.example.e_commerce.data.api.product.paging.ProductPagingSource
import com.example.e_commerce.data.store.CartStore
import com.example.e_commerce.data.store.RecentsStore
import com.example.e_commerce.data.store.UserStore
import com.example.e_commerce.domain.model.Cart
import com.example.e_commerce.domain.model.ProductQuery
import com.example.e_commerce.domain.repo.ProductRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class ProductRepositoryImpl @Inject constructor(

    private val productApi: ProductApi,

    private val userStore: UserStore,
    private val recentsStore: RecentsStore,
    private val cartStore: CartStore
) : ProductRepository {
    override suspend fun getHome(): HomeResponse {
        val response = productApi.getHome()
        userStore.set(response.user)
        return response
    }

    override suspend fun getCategories() = productApi.getCategories()
    override fun getProducts(query: ProductQuery) = Pager(
        config = PagingConfig(
            pageSize = 10,
            prefetchDistance = 10,
            enablePlaceholders = false,
            initialLoadSize = 20
        ),
        initialKey = 0,
        pagingSourceFactory = {
            ProductPagingSource(productApi, query)
        }
    ).flow

    override fun getRecentSearchs() = recentsStore.getFlow().map { it?.toList() ?: emptyList() }
    override suspend fun clearRecents() = recentsStore.clear()

    //if there is a new search, it will be add to the recent
    override suspend fun addRecents(search: String) {
        val recents = (recentsStore.get() ?: emptyArray()).toMutableList()
        recents.remove(search) //If it exists, it will be deleted and put on the first line
        recents.add(0, search)
        recentsStore.set(recents.toTypedArray())
    }

    override suspend fun getProduct(id: String) = productApi.getProduct(id)
    override suspend fun toggleWishlist(productId: String, wishlist: Boolean) {
        productApi.toggleWishlist(productId, wishlist)
    }

    override suspend fun setCart(cart: Cart) {
        val carts = (cartStore.get() ?: emptyArray())
            .toList()
            .filterNot { it.id == cart.id }
            .toMutableList()
        if (cart.count > 0) {
            carts.add(cart)
        }
        cartStore.set(carts.toTypedArray())
    }

    override fun getCarts() = cartStore.getFlow().map { it?.toList() ?: emptyList() }

    override suspend fun clearCart() = cartStore.clear()


}



