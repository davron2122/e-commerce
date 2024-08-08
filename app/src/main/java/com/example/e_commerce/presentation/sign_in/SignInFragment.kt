package com.example.e_commerce.presentation.sign_in

import android.os.Bundle
import android.view.View
import com.example.e_commerce.databinding.FragmentSignInBinding
import com.example.e_commerce.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : BaseFragment<FragmentSignInBinding>(FragmentSignInBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}