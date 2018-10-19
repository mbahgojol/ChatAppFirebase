package com.module.codestyle.ui.main.fragmenttwo;

import android.databinding.ObservableArrayList;

import com.module.codestyle.conn.ApiService;
import com.module.codestyle.ui.base.BaseViewModel;

import java.util.List;

/**
 * Created by knalb on 19/10/18.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer
 */
public class TwoViewModel extends BaseViewModel<TwoNavigator> {
    public ObservableArrayList<String> listTwo = new ObservableArrayList();
    public ApiService apiService;

    public TwoViewModel(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
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