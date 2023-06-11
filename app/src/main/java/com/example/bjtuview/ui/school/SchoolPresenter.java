package com.example.bjtuview.ui.school;

import android.util.AndroidException;

import com.example.bjtuview.bean.BaseBean;
import com.example.bjtuview.bean.Goods;
import com.example.bjtuview.ui.school.adapter.SchoolModel;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SchoolPresenter implements SchoolContract.ISchoolPresenter{

    private SchoolContract.ISchoolModel schoolModel;
    private SchoolContract.ISchoolView schoolView;
    public SchoolPresenter(SchoolContract.ISchoolView schoolView){
        this.schoolView=schoolView;
        schoolModel=new SchoolModel();
    }
    @Override
    public void getData() {
        schoolModel.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean<List<Goods>>>() {
                    @Override
                    public void accept(BaseBean<List<Goods>> listBaseBean) throws Throwable {
                        schoolView.getGoodsSuccess(listBaseBean.getData());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        schoolView.getGoodsError(throwable);
                    }
                });
    }
}
