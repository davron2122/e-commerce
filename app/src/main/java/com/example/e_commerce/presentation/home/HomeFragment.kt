package com.example.e_commerce.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.e_commerce.databinding.FragmentHomeBinding
import com.example.e_commerce.util.BaseFragment
import com.example.e_commerce.util.setLightStatusBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {


     private  val viewModel by viewModels<HomeViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToLiveData()
        initUi()


    }

    private fun initUi() = with(binding) {
        //for LightStatusBar
        setLightStatusBar()


    }

}