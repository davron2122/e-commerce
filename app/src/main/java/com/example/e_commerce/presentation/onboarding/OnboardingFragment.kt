package com.example.e_commerce.presentation.onboarding

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.e_commerce.R
import com.example.e_commerce.databi.OnboardingAdapter

import com.example.e_commerce.databinding.FragmentOnboardingBinding
import com.example.e_commerce.util.BaseFragment
import com.example.e_commerce.util.clearLightStatusBar
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingFragment :
    BaseFragment<FragmentOnboardingBinding>(FragmentOnboardingBinding::inflate) {

    private val viewModel by viewModels<OnboardingViewModel>()


    private val adapter = OnboardingAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initUi()

    }

    private fun initUi() = with(binding) {

        clearLightStatusBar()

        indicatorView.apply {
            val normalColor = ContextCompat.getColor(requireContext(), R.color.indicator_unchecked)
            val checkedColor = ContextCompat.getColor(requireContext(), R.color.indicator_checked)
            setSliderColor(normalColor, checkedColor)
            setSliderWidth(resources.getDimension(R.dimen.dp_10))
            setSliderHeight(resources.getDimension(R.dimen.dp_8))
            setSlideMode(IndicatorSlideMode.WORM)
            setIndicatorStyle(IndicatorStyle.ROUND_RECT)
            setPageSize(adapter.itemCount)
            notifyDataChanged()
        }
        next.setOnClickListener {
            if (pager.currentItem == adapter.itemCount - 1) { //oxirgi page bolsa
                viewModel.onboarded()
                findNavController().navigate(OnboardingFragment.())
            } else {
                pager.setCurrentItem(pager.currentItem + 1, true)
            }

        }
    }
}