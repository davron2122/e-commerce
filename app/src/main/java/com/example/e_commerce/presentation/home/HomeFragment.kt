package com.example.e_commerce.presentation.home

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.e_commerce.R
import com.example.e_commerce.databinding.FragmentHomeBinding
import com.example.e_commerce.util.BaseFragment
import com.example.e_commerce.util.setLightStatusBar
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {


    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToLiveData()
        initUi()


    }
    private fun initUi() = with(binding) {
        //for LightStatusBar
        setLightStatusBar()

        error.retry.setOnClickListener {
            viewModel.getHome()
        }



        indicator.apply {
            val normalColor = ContextCompat.getColor(requireContext(), R.color.indicator_unchecked)
            val checkedColor = ContextCompat.getColor(requireContext(), R.color.indicator_checked)
            setSliderColor(normalColor, checkedColor)
            setSliderWidth(resources.getDimension(R.dimen.dp_20))
            setSliderHeight(resources.getDimension(R.dimen.dp_4))
            setSlideMode(IndicatorSlideMode.WORM)
            setIndicatorStyle(IndicatorStyle.ROUND_RECT)
            notifyDataChanged()
        }
        banners.offscreenPageLimit = 1

        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx = resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            page.scaleY = 1 - (0.25f * abs(position))

        }
        banners.setPageTransformer(pageTransformer)

        val itemDecoration = HorizontalMarginItemDecoration(
            requireContext(),
            R.dimen.viewpager_current_item_horizontal_margin
        )
        banners.addItemDecoration(itemDecoration)

        showAll.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.toCategoriesFragment())
        }

        // When the view gains focus (i.e., when the user taps on it to enter text), it triggers
        // a navigation event to move to the SearchFragment
        searchContainer.search.setOnFocusChangeListener { view, focused ->
            if (focused.not()) return@setOnFocusChangeListener
            findNavController().navigate(HomeFragmentDirections.toSearchFragment())
        }

    }

    private fun subscribeToLiveData() = with(binding) {
        viewModel.loading.observe(viewLifecycleOwner) {
            loading.root.isVisible = it
        }
        viewModel.error.observe(viewLifecycleOwner) {
            error.root.isVisible = it
        }
        viewModel.home.observe(viewLifecycleOwner) {
            home.isVisible = it != null //UI element visible
            it ?: return@observe

            //show the avatar
            val name = it.user.firstName ?: it.user.username
            greeting.text = getString(R.string.fragment_home_greeting, name)
            Glide.with(root).load(it.user.avatar).into(avatar)

            banners.adapter = BannerAdapter(it.banners, this@HomeFragment::onBannerClick)
            indicator.setupWithViewPager(banners)
            indicator.apply {
                setPageSize(it.banners.size)
                notifyDataChanged()
            }



            categories.adapter =
                HomeCategoryAdapter(it.categories, this@HomeFragment::onCategoryClick)

            sections.adapter = SectionAdapter(
                it.sections,
                this@HomeFragment::showAll,
                this@HomeFragment::onClickProduct,
                this@HomeFragment::wishlist
            )
            count.text = it.notificationCount.toString()
        }
    }

    private fun onBannerClick(banner: Banner) {

    }

    private fun onCategoryClick(category: Category) {
        findNavController().navigate(HomeFragmentDirections.toProductsFragment(category))
    }

    private fun showAll(section: Section) {

    }

    private fun onClickProduct(product: Product) {
        findNavController().navigate(HomeFragmentDirections.toDetailFragment(product.id))
    }

    private fun wishlist(product: Product) {
        viewModel.toggleWishlist(product)
    }

}


