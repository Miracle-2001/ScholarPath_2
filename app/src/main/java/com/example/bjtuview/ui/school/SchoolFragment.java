package com.example.bjtuview.ui.school;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.bjtuview.R;
import com.example.bjtuview.bean.Goods;
import com.example.bjtuview.ui.MainActivity;
import com.example.bjtuview.ui.base.BaseActivity;
import com.example.bjtuview.ui.base.BaseFragment;
import com.example.bjtuview.ui.school.adapter.SchoolRecyclerViewAdapter;
import com.example.bjtuview.ui.school.adapter.SchoolSpanSizeLookup;

import java.util.ArrayList;
import java.util.List;

public class SchoolFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, SchoolContract.ISchoolView, SchoolRecyclerViewAdapter.OnItemClickListener {

    private SchoolRecyclerViewAdapter schoolRecyclerViewAdapter;
    private SchoolPresenter schoolPresenter;
    private SchoolSpanSizeLookup schoolSpanSizeLookup;

    private SwipeRefreshLayout swipeRefreshLayout;
    private static final String TAG = "SchoolFragment";

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.search:
                    msg += "Click edit";
                    break;
            }
            //            if(!msg.equals("")) {
            //                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            //            }
            return true;
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_school;
    }
    @Override
    protected void initViews() {

        swipeRefreshLayout =find(R.id.school_swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(this);

        RecyclerView recyclerView=find(R.id.school_recyclerview);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),4);


        List<Goods> goods=new ArrayList<>();
        schoolSpanSizeLookup= new SchoolSpanSizeLookup(goods);
        gridLayoutManager.setSpanSizeLookup(schoolSpanSizeLookup);
        recyclerView.setLayoutManager(gridLayoutManager);

        schoolRecyclerViewAdapter= new SchoolRecyclerViewAdapter(recyclerView,getActivity(),goods);
        schoolRecyclerViewAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(schoolRecyclerViewAdapter);

        schoolPresenter=new SchoolPresenter(this);
        schoolPresenter.getData();
    }

    public Toolbar.OnMenuItemClickListener changeMenu(){
        Drawable moreIcon = ContextCompat.getDrawable(MainActivity.toolbar.getContext(), R.drawable.baseline_search_24);
        MainActivity.toolbar.setOverflowIcon(moreIcon);
        MainActivity.toolbar.setTitle("首页");
        return onMenuItemClick;
    }

    @Override
    public int getMenuID() {
        return R.menu.menu_school;
    }


    @Override
    public void onPrimaryNavigationFragmentChanged(boolean isPrimaryNavigationFragment) {

        super.onPrimaryNavigationFragmentChanged(isPrimaryNavigationFragment);
        Log.v("SchoolFragment","init school");
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
        schoolPresenter.getData();
    }

    @Override
    public void getGoodsSuccess(List<Goods> goods) {
        schoolSpanSizeLookup.setGoods(goods);
        schoolRecyclerViewAdapter.setGoods(goods);
    }

    @Override
    public void getGoodsError(Throwable throwable) {

    }

    @Override
    public void onItemClick(Goods goods) {
        String imageUrl=goods.getImageUrl();
        String WebPage=goods.getWebPage();
        System.out.println("onItemClick");
        System.out.println(WebPage);
        System.out.println(imageUrl);
        Uri uri = Uri.parse(WebPage);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
        Log.i(TAG, "onItemClick: "+goods);
    }
}
