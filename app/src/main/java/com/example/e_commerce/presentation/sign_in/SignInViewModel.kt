package com.example.e_commerce.presentation.sign_in

import android.provider.CalendarContract.Events
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerce.domain.repo.AuthRepository
import com.example.e_commerce.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    val loading = MutableLiveData(false)
    val events = SingleLiveEvent<Event>()

    fun signIn(username: String, password: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            authRepository.signIn(username, password)
            events.postValue(Event.NavigateToHome)
        } catch (e: Exception) {
            when {
                e is HttpException && e.code() == 404 -> events.postValue(Event.InvalidCredentials)
                e is IOException -> events.postValue(Event.ConnectionError)
                else -> events.postValue(Event.Error)
            }
        }
    }

    sealed class Event {
        object InvalidCredentials : Event()
        object ConnectionError : Event()
        object Error : Event()
        object NavigateToHome : Event()
    }
}