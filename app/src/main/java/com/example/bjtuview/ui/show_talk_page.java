package com.example.bjtuview.ui;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bjtuview.R;
import com.example.bjtuview.ui.talk.answer;
import com.example.bjtuview.ui.talk.answer_adapter;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class show_talk_page extends AppCompatActivity {
    ArrayList<answer> answerlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_talk_page);
        TextView q_title = findViewById(R.id.title);
        q_title.setText("如何报考北京交通大学");
        TextView q_content = findViewById(R.id.content);
        q_content.setText("如题");

        RecyclerView recyclerView;
        recyclerView = findViewById(R.id.recyclerView);

        answerlist= new ArrayList<>();
        answerlist.add(new answer("zky","提交报名表","2023-1-2"));
        answer_adapter adapter = new answer_adapter(this,answerlist);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}

