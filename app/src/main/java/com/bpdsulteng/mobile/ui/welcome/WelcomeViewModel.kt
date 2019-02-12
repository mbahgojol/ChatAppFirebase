package com.bpdsulteng.mobile.ui.welcome

import com.bpdsulteng.mobile.ui.base.BaseObservableViewModel

/**
 * Created by knalb on 04/12/18.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer
 */
class WelcomeViewModel : BaseObservableViewModel<WelcomeNavigator>() {

    internal var viewPagerPageChangeListener: ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener {

        override fun onPageSelected(position: Int) {
            navigator.bottomProgressDots(position)
        }

        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {

        }

        override fun onPageScrollStateChanged(arg0: Int) {

        }
    }

    fun clickedNext() {
        navigator.onClickedNext()
    }
}