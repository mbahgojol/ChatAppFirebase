package com.module.codestyle.utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by knalb on 04/11/18.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer
 */

public class RecyclerViewLoadMoreScroll extends RecyclerView.OnScrollListener {

    private OnLoadMoreListener mOnLoadMoreListener;
    private boolean isLoading;
    private LinearLayoutManager mLayoutManager;

    public void setLoaded() {
        isLoading = false;
    }

    public boolean getLoaded() {
        return isLoading;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }

    public RecyclerViewLoadMoreScroll() {

    }

    public void setLinearLayoutManager(LinearLayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
    }

    public RecyclerViewLoadMoreScroll(LinearLayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (mLayoutManager != null) {
            if (mLayoutManager.getItemCount() > 9) {
                if (!isLoading && mLayoutManager.findLastVisibleItemPosition() == mLayoutManager.getItemCount() - 1) {
                    loadMoreData();
                    isLoading = true;
                }
            }
        }
        super.onScrolled(recyclerView, dx, dy);
    }

    private void loadMoreData() {
        if (mOnLoadMoreListener != null) {
            mOnLoadMoreListener.onLoadMore();
        }
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }
}