package com.bpdsulteng.mobile.utils;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bpdsulteng.mobile.model.WelcomePojo;
import com.bpdsulteng.mobile.ui.main.listdata.AdapterListDataFragment;
import com.bpdsulteng.mobile.ui.welcome.WelcomeViewPagerAdapter;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by knalb on 03/10/18.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer
 */

public final class BindingUtils {

    private BindingUtils() {

    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context).load(url).into(imageView);
    }

    @BindingAdapter("currentItem")
    public static void setCurrentItem(ViewPager viewPager, int item) {
        viewPager.setCurrentItem(item, true);
    }

    @BindingAdapter({"adapterOne"})
    public static void addItemsFragmentOne(RecyclerView recyclerView, List<String> strings) {
        AdapterListDataFragment adapterOneFragment = (AdapterListDataFragment) recyclerView.getAdapter();
        if (adapterOneFragment != null) {
            adapterOneFragment.clearItems();
            adapterOneFragment.addItems(strings);
        }
    }

    @BindingAdapter({"adapter"})
    public static void addItemsViewPagerWelcome(ViewPager viewPager, List<WelcomePojo> items) {
        WelcomeViewPagerAdapter adapterTwoFragment = (WelcomeViewPagerAdapter) viewPager.getAdapter();
        if (adapterTwoFragment != null) {
            adapterTwoFragment.clearItems();
            adapterTwoFragment.addItems(items);
        }
    }
}
