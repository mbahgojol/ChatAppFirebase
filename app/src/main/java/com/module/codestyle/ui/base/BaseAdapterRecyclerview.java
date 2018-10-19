package com.module.codestyle.ui.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by knalb on 18/10/18.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer
 */
public abstract class BaseAdapterRecyclerview<MODEl, BINDING extends ViewDataBinding, VIEWMODEL> extends RecyclerView.Adapter<BaseAdapterRecyclerview<MODEl, BINDING, VIEWMODEL>.BaseViewHolderRecyclerView> {

    protected List<MODEl> items = new ArrayList();

    protected VIEWMODEL viewmodel;

    protected abstract int getLayoutIdForPosition(int position);

    public abstract int getBindingVariable();

    public abstract VIEWMODEL getViewModel(MODEl data, int position);

    public abstract void bind(BINDING binding, MODEl data, int position);

    protected OnItemClickListener<MODEl> itemClickListener;

    public void setOnItemClickListener(OnItemClickListener<MODEl> itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public BaseAdapterRecyclerview() {

    }

    public interface OnItemClickListener<T> {
        void onItemClick(View view, T item);
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    public void clearItems() {
        this.items.clear();
        notifyDataSetChanged();
    }

    public void addItems(List<MODEl> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public BaseAdapterRecyclerview<MODEl, BINDING, VIEWMODEL>.BaseViewHolderRecyclerView onCreateViewHolder(ViewGroup parent, int viewType) {
        return create(LayoutInflater.from(parent.getContext()), parent, viewType);
    }

    @Override
    public void onBindViewHolder(BaseAdapterRecyclerview<MODEl, BINDING, VIEWMODEL>.BaseViewHolderRecyclerView holder, int position) {
        holder.bindto(this.items.get(position), position);
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public BaseAdapterRecyclerview<MODEl, BINDING, VIEWMODEL>.BaseViewHolderRecyclerView create(LayoutInflater inflater, ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, viewType, parent, false);
        return new BaseAdapterRecyclerview<MODEl, BINDING, VIEWMODEL>.BaseViewHolderRecyclerView(binding);
    }


    class BaseViewHolderRecyclerView extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;

        public BaseViewHolderRecyclerView(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindto(MODEl data, int position) {
            viewmodel = viewmodel == null ? getViewModel(data, position) : viewmodel;
            binding.setVariable(getBindingVariable(), viewmodel);
            binding.executePendingBindings();
            bind((BINDING) binding, data, position);
        }
    }
}


