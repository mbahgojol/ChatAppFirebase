package com.bpdsulteng.mobile.ui.main.listdata;

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
public class ListDataViewModel extends BaseObservableViewModel<ListDataNavigator> {
    private ApiManager apiManager;
    private ObservableArrayList<String> listOne = new ObservableArrayList();

    public void init() {

    }

    public ListDataViewModel(ApiManager apiManager) {
        this.apiManager = apiManager;
    }


    public void setListOne(List<String> data) {
        listOne.clear();
        listOne.addAll(data);
    }

    public ObservableArrayList<String> getListOne() {
        return listOne;
    }
}
