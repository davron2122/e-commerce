package com.example.e_commerce.presentation.sign_in

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.e_commerce.R
import com.example.e_commerce.databinding.FragmentSignInBinding
import com.example.e_commerce.util.BaseFragment
import com.example.e_commerce.util.clearLightStatusBar
import com.example.e_commerce.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : BaseFragment<FragmentSignInBinding>(FragmentSignInBinding::inflate) {

    private val viewModel by viewModels<SignInViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        subscribeToLiveData()


    }

    private fun subscribeToLiveData() = with(binding) {
        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            progress.isVisible = isLoading
            singInBtn.text = if (isLoading) null else getString(R.string.sign_in_button)
        }
        viewModel.events.observe(viewLifecycleOwner) { event ->
            when (event) {
                SignInViewModel.Event.ConnectionError -> toast(R.string.connection_error)
                SignInViewModel.Event.Error -> toast(R.string.error)
                SignInViewModel.Event.InvalidCredentials -> toast(R.string.invalid_credentials)
                SignInViewModel.Event.NavigateToHome -> toast(R.string.app_name)
            }
        }
    }

    private fun initUi() = with(binding) {

        //for clearLightStatusBar
        clearLightStatusBar()

        singInBtn.setOnClickListener {
            viewModel.signIn(username.text.toString(), password.text.toString())
        }

        singUp.setOnClickListener {
            findNavController().navigate(SignInFragmentDirections.toSignUpFragment())

            //
        }
    }
}
