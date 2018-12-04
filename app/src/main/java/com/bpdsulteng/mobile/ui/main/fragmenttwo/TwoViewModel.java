package com.bpdsulteng.mobile.ui.main.fragmenttwo;

import android.databinding.ObservableArrayList;

import com.bpdsulteng.mobile.conn.ApiManager;
import com.bpdsulteng.mobile.ui.base.BaseObservableViewModel;

import java.util.List;

/**
 * Created by knalb on 19/10/18.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer
 */
public class TwoViewModel extends BaseObservableViewModel<TwoNavigator> {
    public ObservableArrayList<String> listTwo = new ObservableArrayList();
    public ApiManager apiManager;

    public TwoViewModel(ApiManager apiManager) {
        this.apiManager = apiManager;
    }

    public void init() {

    }

    public void setListTwo(List<String> data) {
        listTwo.clear();
        listTwo.addAll(data);
    }

    public ObservableArrayList<String> getListTwo() {
        return listTwo;
    }
}
