package com.example.e_commerce.data.api.auth.dto

import com.google.gson.annotations.SerializedName

class SignUpRequest (
    @SerializedName("username")
    val username:String,
    @SerializedName("email")
    val email:String,
    @SerializedName("password")
    val password:String



){

}


