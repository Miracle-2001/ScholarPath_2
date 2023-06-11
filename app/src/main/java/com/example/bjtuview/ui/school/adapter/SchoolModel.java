package com.example.bjtuview.ui.school.adapter;

import com.example.bjtuview.bean.BaseBean;
import com.example.bjtuview.bean.Goods;
import com.example.bjtuview.network.RetrofitClient;
import com.example.bjtuview.network.service.GoodsService;
import com.example.bjtuview.ui.school.SchoolContract;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class SchoolModel implements SchoolContract.ISchoolModel {

    @Override
    public Flowable<BaseBean<List<Goods>>> getData() {
        return RetrofitClient.getInstance().getService(GoodsService.class)
                .getGoods();
    }
}
