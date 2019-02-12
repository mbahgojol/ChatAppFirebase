package com.bpdsulteng.mobile.ui.welcome

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.bpdsulteng.mobile.BR
import com.bpdsulteng.mobile.R
import com.bpdsulteng.mobile.databinding.ActivityWelcomeBinding
import com.bpdsulteng.mobile.model.WelcomePojo
import com.bpdsulteng.mobile.ui.base.BaseActivity
import com.bpdsulteng.mobile.ui.main.MainActivity
import com.bpdsulteng.mobile.utils.Tools
import com.bpdsulteng.mobile.widget.ParallaxPageTransformer
import com.bpdsulteng.mobile.widget.ParallaxPageTransformer.ParallaxTransformInformation
import com.bpdsulteng.mobile.widget.ParallaxPageTransformer.ParallaxTransformInformation.PARALLAX_EFFECT_DEFAULT
import javax.inject.Inject


class WelcomeActivity : BaseActivity<ActivityWelcomeBinding, WelcomeViewModel>(), WelcomeNavigator {
    private val MAX_STEP = 4
    private lateinit var binding: ActivityWelcomeBinding
    @Inject
    internal lateinit var viewModel: WelcomeViewModel
    @Inject
    internal lateinit var adapter: WelcomeViewPagerAdapter
    private var items: MutableList<WelcomePojo> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewDataBinding
        viewModel.navigator = this
        bottomProgressDots(0)
        items = initData()
        setupView()
    }

    private fun initData(): MutableList<WelcomePojo> {
        var title = resources.getStringArray(R.array.title_welcome)
        var des = resources.getStringArray(R.array.des_welcome)
        var img = resources.obtainTypedArray(R.array.img_welcome)
        var data: MutableList<WelcomePojo> = mutableListOf()
        title.forEachIndexed { index, s ->
            var msg = WelcomePojo(title[index], des[index], img.getResourceId(index, -1))
            data.add(msg)
        }
        return data
    }

    private fun setupView() {
        view_pager.adapter = adapter
        adapter.addItems(items)

        view_pager.addOnPageChangeListener(viewModel.viewPagerPageChangeListener)

        view_pager.offscreenPageLimit = 4
        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            @SuppressLint("SetTextI18n")
            override fun onPageSelected(position: Int) {
                if (view_pager.currentItem == items.size - 1) {
                    btn_next.text = "Get Started"
                } else {
                    btn_next.text = "Next"
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        val pageTransformer = ParallaxPageTransformer()
                .addViewToParallax(ParallaxTransformInformation(R.id.image, PARALLAX_EFFECT_DEFAULT, 2f, true))
                .addViewToParallax(ParallaxTransformInformation(R.id.description, PARALLAX_EFFECT_DEFAULT, 2f, false))
                .addViewToParallax(ParallaxTransformInformation(R.id.title, -3f, 3f, false))
        view_pager.setPageTransformer(true, pageTransformer)

        Tools.setSystemBarColor(this, R.color.grey_10)
        Tools.setSystemBarLight(this)
    }

    override fun onClickedNext() {
        val current = view_pager.currentItem + 1
        if (current < MAX_STEP) {
            // move to next screen
            view_pager.currentItem = current
        } else {
            startActivity<MainActivity>()
            finish()
        }
    }


    override fun bottomProgressDots(current_index: Int) {
        val dots = arrayOfNulls<ImageView>(MAX_STEP)

        layoutDots.removeAllViews()
        for (i in dots.indices) {
            dots[i] = ImageView(this)
            val width_height = 15
            val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams(width_height, width_height))
            params.setMargins(10, 10, 10, 10)
            dots[i]?.layoutParams = params
            dots[i]?.setImageResource(R.drawable.shape_circle)
            dots[i]?.setColorFilter(resources.getColor(R.color.grey_20), PorterDuff.Mode.SRC_IN)
            layoutDots.addView(dots[i])
        }

        if (dots.size > 0) {
            dots[current_index]?.setImageResource(R.drawable.shape_circle)
            dots[current_index]?.setColorFilter(resources.getColor(R.color.light_green_600), PorterDuff.Mode.SRC_IN)
        }
    }

    override fun getBindingVariable(): Int {
        return BR.vmwelcom
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_welcome
    }

    override fun getViewModel(): WelcomeViewModel {
        return viewModel
    }
}
