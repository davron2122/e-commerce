package com.example.e_commerce.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.analytics.ecommerce.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(){
    val loading = MutableLiveData(false)
    val error = MutableLiveData(false)
    init {
        getHome()

    }

    fun getHome() = viewModelScope.launch {

        loading.postValue(true)
        error.postValue(false)

        try {
            val response = productRepository.getHome()
            home.postValue(response)
        }catch (e:Exception){
            error.postValue(true)

        }finally {
            loading.postValue(false)
        }
    }

    fun toggleWishlist(product:Product) = viewModelScope.launch {
        try {
            productRepository.toggleWishlist(product.id,product.wishlist)
        }catch (e:Exception){

        }
    }

}

