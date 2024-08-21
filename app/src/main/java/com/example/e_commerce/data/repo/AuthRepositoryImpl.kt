package com.example.e_commerce.data.repo

import com.example.e_commerce.data.api.auth.AuthApi
import com.example.e_commerce.data.api.auth.dto.AuthResponse
import com.example.e_commerce.data.api.auth.dto.SignInRequest
import com.example.e_commerce.data.api.auth.dto.SignUpRequest
import com.example.e_commerce.data.store.OnboardedStore
import com.example.e_commerce.data.store.UserStore
import com.example.e_commerce.domain.model.Destination
import com.example.e_commerce.domain.repo.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val tokenStore: TokenStore,
    private val userStore: UserStore,
    private val onboardedStore: OnboardedStore
) : AuthRepository {
    override suspend fun signIn(username: String, password: String) {
        val request = SignInRequest(username, password)
        val response = authApi.signIn(request)
        saveUserInfo(response)

    }

    override suspend fun signUp(username: String, email: String, password: String) {
        val request = SignUpRequest(username, email, password)
        val response = authApi.signUp(request)
        saveUserInfo(response)
    }
//destination
    override fun destinationFlow() = channelFlow {
        suspend fun sendDestination() {
            when {
                tokenStore.get() != null -> send(Destination.Home)
                onboardedStore.get() == true -> send(Destination.Auth)
                else -> send(Destination.Onboarding)
            }
        }
        launch {
            tokenStore.getFlow().collectLatest {
                sendDestination()
            }
        }
        launch {
            onboardedStore.getFlow().collectLatest {
                sendDestination()
            }
        }
    }.distinctUntilChanged() //for example, if home comes twice, it will not transfer
    // the second one, that is, until it changes

    override suspend fun onboarded() = onboardedStore.set(true)

    private suspend fun saveUserInfo(response: AuthResponse) {
        tokenStore.set(response.token)
        userStore.set(response.user)
    }
}

