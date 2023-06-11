package com.example.bjtuview.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.bjtuview.R;
import com.example.bjtuview.ui.base.BaseActivity;
import com.example.bjtuview.ui.base.BaseFragment;
import com.example.bjtuview.ui.mine.MineFragment;
import com.example.bjtuview.ui.query.QueryFragment;
import com.example.bjtuview.ui.school.SchoolFragment;
import com.example.bjtuview.ui.talk.TalkFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    @SuppressLint("StaticFieldLeak")
    public static Toolbar toolbar;//这里toolbar需要设置为static，因为需要从Fragment调用
    private Menu myMenu;
    private Fragment[] fragments;//fragment的编号0，1，2，3
    private int lastFragmentIndex=-1;//上一个Fragment编号，初始是-1
    @Override
    protected void onCreate(Bundle savedInstanceState) {//初始化activity
        setTheme(R.style.Theme_ScholarPath);
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//初始化菜单栏，在initViews之后会自动调用它
        myMenu=menu;
//        getMenuInflater().inflate(R.menu.menu_school, menu);
        switchFragment(0);
        setFragmentMenu(0);
        return true;
    }
    public void close(){
        finish();
    }
    @Override
    protected void initViews() {//初始化界面，控制toolbar和Fragment
        
        toolbar=find(R.id.toolbar);
//        Drawable moreIcon = ContextCompat.getDrawable(toolbar.getContext(), R.drawable.baseline_search_24);
//        toolbar.setOverflowIcon(moreIcon);

        BottomNavigationView bottomNavigationView=find(R.id.main_bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        fragments=new Fragment[]{
                new SchoolFragment(),
                new TalkFragment(),
                new QueryFragment(),
                new MineFragment()
        };
        getSupportFragmentManager().beginTransaction().add(R.id.main_frame,fragments[0])
                .commit();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item){//响应底部导航栏的选择
        item.setChecked(true);
        switch (item.getItemId()){
            case R.id.button_school:
                switchFragment(0);
                setFragmentMenu(0);
                break;
            case R.id.button_talk:
                switchFragment(1);
                setFragmentMenu(1);

                break;
            case R.id.button_query:
                switchFragment(2);
                setFragmentMenu(2);

                break;
            case R.id.button_mine:
                switchFragment(3);
                setFragmentMenu(3);
                break;
        }
        return false;
    }
    public void setFragmentMenu(int id){//根据Fragment的id设置改变菜单栏项目
        myMenu.clear();
        BaseFragment fragment=(BaseFragment) fragments[id];
        Toolbar.OnMenuItemClickListener onMenuItemClick=fragment.changeMenu();
        toolbar.setOnMenuItemClickListener(onMenuItemClick);
        assert (myMenu!=null);
//        getMenuInflater().inflate(R.menu.menu_school, myMenu);
        getMenuInflater().inflate(fragment.getMenuID(), myMenu);
    }

    private void switchFragment(int to){ //Fragment切换
        if (lastFragmentIndex==to){
            return ;
        }
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        if(!fragments[to].isAdded()){
            fragmentTransaction.add(R.id.main_frame,fragments[to]);
        }else{
            fragmentTransaction.show(fragments[to]);
        }
        if(lastFragmentIndex>=0){
            fragmentTransaction.hide(fragments[lastFragmentIndex])
                    .commitAllowingStateLoss();
        }

        lastFragmentIndex=to;
    }
}
