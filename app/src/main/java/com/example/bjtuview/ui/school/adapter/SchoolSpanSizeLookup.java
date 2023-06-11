package com.example.bjtuview.ui.school.adapter;

import androidx.recyclerview.widget.GridLayoutManager;

import com.example.bjtuview.bean.Goods;
import com.example.bjtuview.ui.school.SchoolFragment;

import java.util.List;

public class SchoolSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

    private final List<Goods>data;
    public SchoolSpanSizeLookup(List<Goods> data){
        this.data=data;
    }
    @Override
    public int getSpanSize(int position) {
        return data.get(position).getSpanSize();
    }

    public void setGoods(List<Goods> data){
        this.data.clear();
        this.data.addAll(data);
    }
}
