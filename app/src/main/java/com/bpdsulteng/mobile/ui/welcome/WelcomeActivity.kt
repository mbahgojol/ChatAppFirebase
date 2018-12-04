package com.bpdsulteng.mobile.ui.welcome

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bpdsulteng.mobile.R
import com.bpdsulteng.mobile.utils.Tools
import com.bpdsulteng.mobile.widget.ParallaxPageTransformer
import com.bpdsulteng.mobile.widget.ParallaxPageTransformer.ParallaxTransformInformation
import com.bpdsulteng.mobile.widget.ParallaxPageTransformer.ParallaxTransformInformation.PARALLAX_EFFECT_DEFAULT
import kotlinx.android.synthetic.main.activity_welcome.*


class WelcomeActivity : AppCompatActivity() {

    private val MAX_STEP = 4
    private val about_title_array = arrayOf("Ready to Travel", "Pick the Ticket", "Flight to Destination", "Enjoy Holiday")
    private val about_description_array = arrayOf("Choose your destination, plan Your trip. Pick the best place for Your holiday", "Select the day, pick Your ticket. We give you the best prices. We guarantee!", "Safe and Comfort flight is our priority. Professional crew and services.", "Enjoy your holiday, Don't forget to feel the moment and take a photo!")
    private val about_images_array = intArrayOf(R.drawable.img_wizard_1, R.drawable.img_wizard_2, R.drawable.img_wizard_3, R.drawable.img_wizard_4)
    private var myViewPagerAdapter: MyViewPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        bottomProgressDots(0)

        myViewPagerAdapter = MyViewPagerAdapter()
        view_pager.adapter = myViewPagerAdapter
        view_pager.addOnPageChangeListener(viewPagerPageChangeListener)

        view_pager.offscreenPageLimit = 4
        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            @SuppressLint("SetTextI18n")
            override fun onPageSelected(position: Int) {
                if (view_pager.currentItem == about_title_array.size - 1) {
                    btn_next.text = "Get Started"
                } else {
                    btn_next.text = "Next"
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        val pageTransformer = ParallaxPageTransformer()
                .addViewToParallax(ParallaxTransformInformation(R.id.image, PARALLAX_EFFECT_DEFAULT, PARALLAX_EFFECT_DEFAULT, true))
                .addViewToParallax(ParallaxTransformInformation(R.id.title, -10f, 10f, false))
        view_pager.setPageTransformer(true, pageTransformer)

        btn_next.setOnClickListener {
            val current = view_pager.currentItem + 1
            if (current < MAX_STEP) {
                // move to next screen
                view_pager.currentItem = current
            } else {
                finish()
            }
        }

        Tools.setSystemBarColor(this, R.color.grey_10)
        Tools.setSystemBarLight(this)
    }


    private fun bottomProgressDots(current_index: Int) {
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

    internal var viewPagerPageChangeListener: ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener {

        override fun onPageSelected(position: Int) {
            bottomProgressDots(position)
        }

        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {

        }

        override fun onPageScrollStateChanged(arg0: Int) {

        }
    }


    inner class MyViewPagerAdapter : PagerAdapter() {
        private var layoutInflater: LayoutInflater? = null

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            layoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            val view = layoutInflater!!.inflate(R.layout.item_welcome, container, false)
            (view.findViewById(R.id.title) as TextView).text = about_title_array[position]
            (view.findViewById(R.id.description) as TextView).text = about_description_array[position]
            (view.findViewById(R.id.image) as ImageView).setImageResource(about_images_array[position])

            container.addView(view)
            return view
        }

        override fun getCount(): Int {
            return about_title_array.size
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view === obj
        }


        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            val view = `object` as View
            container.removeView(view)
        }
    }
}
