package com.module.codestyle.utils;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.module.codestyle.ui.main.fragmentone.AdapterOneFragment;
import com.module.codestyle.ui.main.fragmenttwo.AdapterTwoFragment;

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
        AdapterOneFragment adapterOneFragment = (AdapterOneFragment) recyclerView.getAdapter();
        if (adapterOneFragment != null) {
            adapterOneFragment.clearItems();
            adapterOneFragment.addItems(strings);
        }
    }

    @BindingAdapter({"adapterTwo"})
    public static void addItemsFragmentTwo(RecyclerView recyclerView, List<String> strings) {
        AdapterTwoFragment adapterTwoFragment = (AdapterTwoFragment) recyclerView.getAdapter();
        if (adapterTwoFragment != null) {
            adapterTwoFragment.clearItems();
            adapterTwoFragment.addItems(strings);
        }
    }
}
