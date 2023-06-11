package com.example.bjtuview.ui.mine;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.bjtuview.R;
import com.example.bjtuview.ui.LoginPage;
import com.example.bjtuview.ui.MainActivity;
import com.example.bjtuview.ui.base.BaseFragment;


/**
 *  我的页面
 *  只处理了关于我们和切换账号
 */
public class MineFragment extends BaseFragment {

    private View Logout,Info,About;
    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.change_theme:
                    msg += "Click edit";
                    break;
                case R.id.change_password:
                    msg += "Click share";
                    break;
            }
            return true;
        }
    };
    @Override
    protected void initViews() {
        Logout=contentView.findViewById(R.id.llLogout);

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setTitle("确定退出吗？")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(MineFragment.this.getContext(), LoginPage.class));
                                MainActivity myMainActivity=(MainActivity)getActivity();
                                myMainActivity.close();
                            }
                        })
                        .show();
            }
        });

        About=contentView.findViewById(R.id.llaboutus);

        About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AboutUS(view);
            }
        });

    }
    private void AboutUS(View view) {
        AlertDialog.Builder inputDialog = new AlertDialog.Builder(getContext());
        inputDialog.setTitle("关于我们")
//                .setView(layout)
                .setIcon(R.drawable.icon)
                .setMessage("\"研途指南\"面向志在保送研究生的大学生" +
                        "包含推免信息、保研咨询、简历评价、个性化等功能\n" +
                        "数据来源于远程github服务器，可能需要魔法\n"+
                        "(其他功能敬请期待~)"
                )
                .setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                dialogInterface.dismiss();
                            }
                        });
        inputDialog.create().show();
    }

    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public Toolbar.OnMenuItemClickListener changeMenu() {
        Drawable moreIcon = ContextCompat.getDrawable(MainActivity.toolbar.getContext(), R.drawable.baseline_settings_24);
        MainActivity.toolbar.setOverflowIcon(moreIcon);
        MainActivity.toolbar.setTitle("我的");
        return onMenuItemClick;
    }

    @Override
    public int getMenuID() {
        return R.menu.menu_mine;
    }
}
