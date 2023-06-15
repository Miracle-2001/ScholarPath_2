package com.example.bjtuview.ui.talk;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bjtuview.R;
import com.example.bjtuview.ui.MainActivity;
import com.example.bjtuview.ui.base.BaseFragment;
import com.example.bjtuview.ui.mine.MineFragment;
import com.example.bjtuview.ui.query.QueryFragment;
import com.example.bjtuview.ui.school.SchoolFragment;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * 论坛
 */
public class TalkFragment extends BaseFragment {

    private ArrayList<talk_profile> talk_list;
    private View Logout,Info,About;
    private Fragment[] fragments;
    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.new_topic:
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("发起话题");  // 设置对话框标题

                    LayoutInflater inflater = getLayoutInflater();
                    View dialogView = inflater.inflate(R.layout.new_talk,null);

                    EditText title = dialogView.findViewById(R.id.title);
                    EditText content = dialogView.findViewById(R.id.content);

                    builder.setView(dialogView);

                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String inputtitle = title.getText().toString();
                            String inputcontent = title.getText().toString();
                            Calendar calendar = Calendar.getInstance();
                            String time = calendar.getTime().toString();
                            talk_list.add(new talk_profile(inputtitle,inputcontent,"MinerTocat",3,time));
                        }
                    });

                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 在这里处理用户点击取消按钮后的操作
                            dialog.dismiss();  // 关闭对话框
                        }
                    });


                    AlertDialog dialog = builder.create();
                    dialog.show();

                    break;
                case R.id.my_topic:
//                    msg += "Click share";
                    break;
                case R.id.record:
                    msg += "Click setting";
                    break;
            }
            //            if(!msg.equals("")) {
            //                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            //            }
            return true;
        }
    };
    @Override
    protected void initViews() {
        RecyclerView recyclerView;
        recyclerView = find(R.id.the_talk);

        talk_list= new ArrayList<>();
        talk_list.add(new talk_profile("如何报名北京交通大学夏令营","如题","MinerTocat",3,"2023-1-2"));
        talk_list.add(new talk_profile("如何报名北京大学夏令营","如题","MinerTocat",3,"2023-1-2"));
        talk_adapter adapter = new talk_adapter(getActivity(),talk_list);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


    }
    protected int getLayoutId() {
        return R.layout.fragment_talk;
    }

    @Override
    public Toolbar.OnMenuItemClickListener changeMenu() {
        Drawable moreIcon = ContextCompat.getDrawable(MainActivity.toolbar.getContext(), R.drawable.baseline_menu_24);
        MainActivity.toolbar.setOverflowIcon(moreIcon);
        MainActivity.toolbar.setTitle("论坛");
        return onMenuItemClick;
    }

    @Override
    public int getMenuID() {
        return R.menu.menu_talk;
    }
}
