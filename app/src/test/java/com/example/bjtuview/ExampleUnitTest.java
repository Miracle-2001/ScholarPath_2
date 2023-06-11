package com.example.bjtuview;

import org.junit.Test;

import static org.junit.Assert.*;

import android.annotation.SuppressLint;

import com.example.bjtuview.bean.BaseBean;
import com.example.bjtuview.bean.Goods;
import com.example.bjtuview.network.RetrofitClient;
import com.example.bjtuview.network.service.GoodsService;

import java.util.List;

import io.reactivex.rxjava3.functions.Consumer;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
        GoodsService goodsService= RetrofitClient.getInstance().getService(GoodsService.class);
        goodsService.getGoods().subscribe(new Consumer<BaseBean<List<Goods>>>() {
            @Override
            public void accept(BaseBean<List<Goods>> listBaseBean) throws Throwable {
                System.out.println(listBaseBean);
            }
        });

        while(true){

        }
    }
}