package com.wzj.wanandroid.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.navigation.NavigationView;
import com.llw.mvplibrary.base.BaseActivity;
import com.llw.mvplibrary.mvp.MvpActivity;
import com.llw.mvplibrary.network.utils.KLog;
import com.wzj.wanandroid.R;
import com.wzj.wanandroid.bean.WallPaperResponse;
import com.wzj.wanandroid.ui.adapter.WallPaperAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author llw
 */
public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    private Toolbar toolbar;
    private DrawerLayout drawer_layout;
    private NavigationView navigation_view;
    private ViewPager2 viewPager;
    private RadioGroup radioGroup;
    private RadioButton rb_home;
    private RadioButton rb_system;
    private RadioButton rb_chapter;
    private RadioButton rb_project;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigation_view = (NavigationView) findViewById(R.id.navigation_view);
        viewPager = (ViewPager2) findViewById(R.id.viewPager);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        rb_home = (RadioButton) findViewById(R.id.rb_home);
        rb_system = (RadioButton) findViewById(R.id.rb_system);
        rb_chapter = (RadioButton) findViewById(R.id.rb_chapter);
        rb_project = (RadioButton) findViewById(R.id.rb_project);

        //设置Activity的Toolbar
        setSupportActionBar(toolbar);
        //启用返回按钮,在ActionBar的左侧显示一个箭头 点击它的时候,它将调用
        //onOptionsItemSelected方法并将传递给 android.R.id.home
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //设置监听器 ActionBarDrawerToggle是一个开关,用于打开/关闭DrawerLayout抽屉,它将Toolbar和DrawerLayout结合起来使用
        drawerToggle = new ActionBarDrawerToggle(this, drawer_layout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        //将call菜单设置为默认选中
        navigation_view.setCheckedItem(R.id.nav_call);
        navigation_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //处理菜单点击事件,当用户点击任意菜单的时候,都会回调到这里
                switch (item.getItemId()){

                }
                //关闭navigation
                drawer_layout.closeDrawers();
                return true;
            }
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
}
