package com.example.e_commerce.presentation.sign_in

import android.provider.CalendarContract.Events
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_commerce.domain.repo.AuthRepository
import com.example.e_commerce.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(

    private val authRepository: AuthRepository


) : ViewModel() {
    val loading = MutableLiveData(false)
    val events = SingleLiveEvent<Events>()
}