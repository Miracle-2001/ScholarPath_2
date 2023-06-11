package com.example.bjtuview.ui.school;

import com.example.bjtuview.bean.BaseBean;
import com.example.bjtuview.bean.Goods;
import com.example.bjtuview.ui.base.BaseActivity;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface SchoolContract {
    interface ISchoolPresenter{
        void getData();

    }

    interface ISchoolModel{
        Flowable<BaseBean<List<Goods>>> getData();

    }

    interface ISchoolView{
        void getGoodsSuccess(List<Goods> goods);
        void getGoodsError(Throwable throwable);
    }
}
