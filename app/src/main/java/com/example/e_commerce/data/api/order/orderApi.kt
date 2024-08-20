package com.example.e_commerce.data.api.order

import retrofit2.http.POST

interface orderApi {

    @POST("order/get-billing")
    suspend fun getBilling (Body request :: CarRequest):: Billing

    @Post("order