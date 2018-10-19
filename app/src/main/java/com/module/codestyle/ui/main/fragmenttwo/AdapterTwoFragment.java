package com.module.codestyle.ui.main.fragmenttwo;

import com.android.databinding.library.baseAdapters.BR;
import com.module.codestyle.R;
import com.module.codestyle.databinding.ItemTwoBinding;
import com.module.codestyle.ui.base.BaseAdapterRecyclerview;

/**
 * Created by knalb on 19/10/18.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer
 */
public class AdapterTwoFragment extends BaseAdapterRecyclerview<String, ItemTwoBinding, TwoItemViewModel> {
    @Override
    protected int getLayoutIdForPosition(int position) {
        String item = items.get(position);
        if (item instanceof String) {
            return R.layout.item_two;
        }
        throw new RuntimeException("invalid obj");
    }

    @Override
    public int getBindingVariable() {
        return BR.vmitemtwo;
    }

    @Override
    public TwoItemViewModel getViewModel(String data, int position) {
        return new TwoItemViewModel(data);
    }

    @Override
    public void bind(ItemTwoBinding binding, String data, int position) {
        binding.getRoot().setOnClickListener(v -> {
            if (itemClickListener != null)
                itemClickListener.onItemClick(v, data);
        });
    }
}
