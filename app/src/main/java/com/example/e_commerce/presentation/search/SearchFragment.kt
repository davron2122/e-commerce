package com.example.e_commerce.presentation.search

import androidx.fragment.app.viewModels
import com.example.e_commerce.databinding.FragmentSearchBinding
import com.example.e_commerce.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment  : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate){

    private val viewModel by viewModels<SearchViewModel> {  }



}