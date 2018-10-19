package com.module.codestyle.ui.main.fragmenttwo;

import android.databinding.ObservableField;

/**
 * Created by knalb on 19/10/18.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer
 */
public class TwoItemViewModel {
    public ObservableField<String> title = new ObservableField<String>();

    public TwoItemViewModel(String title) {
        this.title.set(title);
    }
}
