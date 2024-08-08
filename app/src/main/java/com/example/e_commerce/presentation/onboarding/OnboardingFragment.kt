package com.example.e_commerce.presentation.onboarding

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.example.e_commerce.R
import com.example.e_commerce.databinding.FragmentOnboardingBinding
import com.example.e_commerce.util.BaseFragment
import com.example.e_commerce.util.clearLightStatusBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingFragment :
    BaseFragment<FragmentOnboardingBinding>(FragmentOnboardingBinding::inflate) {

    private val adapter = OnboardingAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initUi()

    }

    private fun initUi() = with(binding) {

       clearLightStatusBar()

        indicatorView.apply {
            val normalColor =ContextCompat.getColor(requireContext(), R.color.indicator_unchecked)
            val checkedColor = ContextCompat.getColor(requireContext(), R.color.indicator_checked)
            setSliderColor(normalColor, checkedColor)
            setSliderWidth(resources.getDimension(R.dimen.dp_10))
            setSliderHeight(resources.getDimension(R.dimen.dp_8))
        }
    }


}