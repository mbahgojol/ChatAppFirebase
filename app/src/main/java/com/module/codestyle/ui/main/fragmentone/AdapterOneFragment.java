package com.module.codestyle.ui.main.fragmentone;

import com.android.databinding.library.baseAdapters.BR;
import com.module.codestyle.R;
import com.module.codestyle.databinding.ItemOneBinding;
import com.module.codestyle.ui.base.BaseAdapterRecyclerview;

/**
 * Created by knalb on 19/10/18.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer
 */
public class AdapterOneFragment extends BaseAdapterRecyclerview<String, ItemOneBinding, OneItemViewModel> {
    @Override
    protected int getLayoutIdForPosition(int position) {
        String item = items.get(position);
        if (item instanceof String) {
            return R.layout.item_one;
        }
        throw new RuntimeException("invalid obj");
    }

    @Override
    public int getBindingVariable() {
        return BR.vmitemone;
    }

    @Override
    public OneItemViewModel getViewModel(String data, int position) {
        return new OneItemViewModel(data);
    }

    @Override
    public void bind(ItemOneBinding binding, String data, int position) {
        binding.getRoot().setOnClickListener(v -> {
            if (itemClickListener != null)
                itemClickListener.onItemClick(v, data);
        });
    }
}
