package com.bpdsulteng.mobile.utils

import android.databinding.BindingAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.bpdsulteng.mobile.model.User
import com.bpdsulteng.mobile.ui.main.chat.UserAdapter

/**
 * Created by knalb on 03/10/18.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer
 */

class BindingUtils {
    companion object {
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun ImageView.setImageUrl(url: String) {
            val context = context
            //Glide.with(context).load(url).into(this)
        }

        @BindingAdapter("imageResource")
        @JvmStatic
        fun ImageView.setImageResource(resId: Int) {
            setImageResource(resId)
        }

        @BindingAdapter("currentItem")
        @JvmStatic
        fun ViewPager.setCurrentItem(item: Int) {
            setCurrentItem(item, true)
        }

        @BindingAdapter("adapter")
        @JvmStatic
        fun RecyclerView.setChatUserList(items: List<User>) {
            if (adapter is UserAdapter) {
                var adapterUser = this.adapter as UserAdapter
                if (adapterUser != null) {
                    adapterUser.clearItems()
                    adapterUser.addItems(items)
                }
            }
        }
    }
}
