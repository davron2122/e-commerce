package com.example.e_commerce.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.ActivityNavigator
import com.example.e_commerce.domain.model.Destination
import com.example.e_commerce.domain.repo.AuthRepository
import com.example.e_commerce.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authRepository: AuthRepository

) : ViewModel() {

    val events = SingleLiveEvent<Event>()

    init {
        getDestination()

    }

    private fun getDestination() = viewModelScope.launch(Dispatchers.IO) {
        authRepository.destinationFlow().collectLatest {
            events.postValue(Event.NavigateTo(it))
        }
        //we we create the connection
    }

    sealed class Event {
        data class NavigateTo(val destination: Destination) : Event()
    }
}

