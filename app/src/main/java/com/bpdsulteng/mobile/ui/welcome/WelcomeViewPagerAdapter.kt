package com.bpdsulteng.mobile.ui.welcome

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bpdsulteng.mobile.R
import com.bpdsulteng.mobile.databinding.ItemWelcomeBinding
import com.bpdsulteng.mobile.model.WelcomePojo

/**
 * Created by knalb on 05/12/18.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer
 */


class WelcomeViewPagerAdapter : PagerAdapter() {
    private var data: MutableList<WelcomePojo> = mutableListOf()
    private lateinit var binding: ItemWelcomeBinding

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = container.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = DataBindingUtil.inflate(inflater, R.layout.item_welcome, container, false)
        binding.title.text = data[position].title
        binding.description.text = data[position].description
        binding.image.setImageResource(data[position].img)

        container.addView(binding.root)
        return binding.root
    }

    fun clearItems() {
        data.clear()
    }

    fun addItems(data: List<WelcomePojo>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)
    }
}