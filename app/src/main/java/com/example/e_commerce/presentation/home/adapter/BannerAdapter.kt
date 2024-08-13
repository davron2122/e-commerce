package com.example.e_commerce.presentation.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerce.data.api.product.dto.Banner

class BannerAdapter(
    private val banners: List <Banner>,
    private val onClick: (banner: Banner) -> Unit
): RecyclerView.Adapter<BannerAdapter.ViewHolder>(){

    inner class ViewHolder(private val binding: ItemBanner)
}


