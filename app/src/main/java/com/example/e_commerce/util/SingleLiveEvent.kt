package com.example.e_commerce.util

import androidx.lifecycle.MutableLiveData

class SingleLiveEvent <T> : MutableLiveData<T>(){
    companion object{
        private const val TAG = "SingleEvent"
    }
}