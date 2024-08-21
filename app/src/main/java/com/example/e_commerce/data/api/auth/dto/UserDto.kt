package com.example.e_commerce.data.api.auth.dto

import com.example.e_commerce.domain.model.User
import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("username")
    val username: String,
    @SerializedName("avatar")
    val avatar: String?,
    @SerializedName("email")
    val email: String,
    @SerializedName("firstName")
    val firstName: String?,
    @SerializedName("lastName")
    val lastName: String?
) {
    // Converts this userDto object to a User object.
    fun toUser() = User(
        username = username,
        avatar = avatar,
        email = email,
        firstName = firstName,
        lastName = lastName
    )
}
