package com.module.codestyle.ui.main.fragmentone;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.module.codestyle.R;
import com.module.codestyle.databinding.ItemOneBinding;
import com.module.codestyle.databinding.ProgressbarBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by knalb on 19/10/18.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer
 */


public class AdapterOneFragment extends RecyclerView.Adapter {
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    private List<String> item = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public void clearItems() {
        item.clear();
    }

    public void addLoadingView() {
        new Handler().post(() -> {
            item.add(null);
            notifyItemInserted(item.size() - 1);
        });
    }

    public void removeLoadingView() {
        item.remove(item.size() - 1);
        notifyItemRemoved(item.size());
    }

    public void moreItems(List<String> item) {
        this.item.addAll(item);
    }

    public void addItems(List<String> item) {
        this.item.addAll(item);
        notifyDataSetChanged();
    }

    public String getItemAtPosition(int position) {
        return item.get(position);
    }

    void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    interface OnItemClickListener {
        void onItemClick(View view, String obj);
    }

    @Override
    public int getItemViewType(int position) {
        return item.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (viewType == VIEW_ITEM) {
            ItemOneBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_one, parent, false);
            vh = new ItemViewHolder(binding);
        } else {
            ProgressbarBinding binding = DataBindingUtil.inflate(inflater, R.layout.progressbar, parent, false);
            vh = new ProgressViewHolder(binding);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).bind(getItemAtPosition(position));
            ((ItemViewHolder) holder).itemView.setOnClickListener(view -> onItemClickListener.onItemClick(view, getItemAtPosition(position)));
        } else if (holder instanceof ProgressViewHolder) {
            ((ProgressViewHolder) holder).bind();
        }
    }

    @Override
    public int getItemCount() {
        return item == null ? 0 : item.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        ItemOneBinding binding;

        public ItemViewHolder(ItemOneBinding v) {
            super(v.getRoot());
            binding = v;
        }

        public void bind(String items) {
            OneItemViewModel viewModel = new OneItemViewModel(items);
            binding.setVmitemone(viewModel);
        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        ProgressbarBinding binding;

        public ProgressViewHolder(ProgressbarBinding v) {
            super(v.getRoot());
            binding = v;
        }

        public void bind() {
            binding.progressBar.setIndeterminate(true);
        }
    }
}
