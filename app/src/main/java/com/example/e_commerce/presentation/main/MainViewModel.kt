package com.example.e_commerce.presentation.main

import android.provider.CalendarContract.Events
import androidx.lifecycle.ViewModel
import com.example.e_commerce.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(

) : ViewModel(){

    val events = SingleLiveEvent<Events>()



}