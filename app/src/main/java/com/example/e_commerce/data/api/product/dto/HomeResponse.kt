package com.example.e_commerce.data.api.product.dto

import com.example.e_commerce.data.api.auth.dto.UserDto
import com.google.gson.annotations.SerializedName

data class HomeResponse(
    val banners: List<Banner>,
    val categories: List<Category>,
    val sections: List<Section>,
    val user: UserDto,
    @SerializedName("notification_count")
    val notificationCount:Int

)