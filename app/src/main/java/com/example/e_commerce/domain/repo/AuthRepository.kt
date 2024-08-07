package com.example.e_commerce.domain.repo

import androidx.navigation.ActivityNavigator
import kotlinx.coroutines.flow.Flow


/**
 * Flows are a part of Kotlin Coroutines and are used for asynchronous stream processing.
 * A Flow is a cold asynchronous stream that can emit multiple values sequentially or asynchronously.
 */

interface AuthRepository {

    suspend fun signIn(username: String, password: String)
    suspend fun signUp(username: String, email: String, password: String)


    fun destinationFlow(): Flow<ActivityNavigator.Destination> //for destination
    suspend fun onboarded()

}