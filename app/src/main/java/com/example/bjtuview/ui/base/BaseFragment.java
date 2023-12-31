package com.example.bjtuview.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
/**
 * 基类Fragment
 */
public abstract class BaseFragment extends Fragment {
    protected View contentView;
    protected abstract void initViews();
    protected abstract int getLayoutId();

    public abstract Toolbar.OnMenuItemClickListener changeMenu();
    public abstract int getMenuID();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView= inflater.inflate(getLayoutId(),container,false);
        initViews();
        return contentView;
    }

    protected <T extends View> T find(@IdRes int id){
        return contentView.findViewById(id);
    }
}
