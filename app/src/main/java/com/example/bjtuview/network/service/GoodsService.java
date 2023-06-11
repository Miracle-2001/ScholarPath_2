package com.example.bjtuview.network.service;

import com.example.bjtuview.bean.BaseBean;
import com.example.bjtuview.bean.Goods;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoodsService {

    @GET("/Miracle-2001/BJTUView/main/item_list")
    Flowable<BaseBean<List<Goods>>> getGoods();

}
