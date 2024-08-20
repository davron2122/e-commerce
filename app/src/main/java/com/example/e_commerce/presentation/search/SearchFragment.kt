package com.example.e_commerce.presentation.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.e_commerce.databinding.FragmentSearchBinding
import com.example.e_commerce.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment  : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate){

    private val viewModel by viewModels<SearchViewModel> ()

   // It is used to retrieve the arguments passed to the current fragment from the navigation graph.
    private val args by navArgs<SearchFragment>()
    private val adapter by lazy {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun subscribeToLiveData ()= with (binding){
        viewModel.loading.observe(viewLifecycleOwner){

        }
    }


}