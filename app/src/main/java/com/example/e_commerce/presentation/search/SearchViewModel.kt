package com.example.e_commerce.presentation.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.e_commerce.data.api.product.dto.Category
import com.example.e_commerce.data.api.product.dto.Product
import com.example.e_commerce.domain.model.ProductQuery
import com.example.e_commerce.domain.repo.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val productRepository: ProductRepository

) : ViewModel() {
    val loading = MutableLiveData(false)
    //It holds the data fetched from the repository, using the Paging library's PagingData type.

    val products = MutableLiveData<PagingData<Product>>()
    val query = MutableLiveData(ProductQuery())
    val recents = MutableLiveData<List<String>>()

    //It keeps coming when we change it in the store

    init {
        getRecents()
    }

    private fun getProducts() = viewModelScope.launch {
        productRepository.getProducts(query.value!!).cachedIn(viewModelScope).collect {
            products.postValue(it)
        }
    }

    // get Products by view model is not enough way for it OrdersFragment and viewModelScope products. query

    fun setInitials(category: Category?, wishlist: Boolean) {
        query.postValue(query.value!!.copy(category = category, favorite = wishlist))
        getProducts()
    }

    fun setSearch(search: String) {
        query.postValue(query.value!!.copy(search = search))
        addRecent(search)
        getProducts()
    }

    //CombinedLoadStates object as parameter, representing the combined loading states of different data sources.


    private fun getRecents() = viewModelScope.launch {


        productRepository.getRecentSearchs().collectLatest {
            recents.postValue(it)
        }
    }


    private fun addRecent(search: String) = viewModelScope.launch {
        productRepository.addRecents(search)
    }

    // resolving a this creating

    fun setQuery(query: ProductQuery) {
        this.query.postValue(query)
        getProducts()

    }

    fun setLoadState(states: CombinedLoadStates) {
        val loading = states.source.refresh is LoadState.Loading
        this.loading.postValue(loading)

    }

    fun clearRecents() = viewModelScope.launch {
        productRepository.clearRecents()
    }
}

