package com.example.bjtuview.ui.query;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.bjtuview.R;
import com.example.bjtuview.ui.MainActivity;
import com.example.bjtuview.ui.base.BaseFragment;
/**
 * 咨询
 */
public class QueryFragment extends BaseFragment {


    private View Logout,Info,About;
    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.new_chat:
                    msg += "Click edit";
                    break;
                case R.id.chat_settings:
                    msg += "Click share";
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

    }

    protected int getLayoutId() {
        return R.layout.fragment_query;
    }

    @Override
    public Toolbar.OnMenuItemClickListener changeMenu() {
        Drawable moreIcon = ContextCompat.getDrawable(MainActivity.toolbar.getContext(), R.drawable.baseline_more_24);
        MainActivity.toolbar.setOverflowIcon(moreIcon);
        MainActivity.toolbar.setTitle("咨询");
        return onMenuItemClick;
    }

    @Override
    public int getMenuID() {
        return R.menu.menu_query;
    }
}
