package com.bpdsulteng.mobile.ui.main.fragmentone;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import com.bpdsulteng.mobile.BR;

/**
 * Created by knalb on 19/10/18.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer
 */
public class OneItemViewModel extends BaseObservable {
    public ObservableField<String> title = new ObservableField<String>();

    public OneItemViewModel(String title) {
        this.title.set(title);
        notifyPropertyChanged(BR.vmitemone);
    }
}
