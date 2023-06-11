package com.wzj.wanandroid.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.llw.mvplibrary.base.BaseActivity;
import com.llw.mvplibrary.network.utils.StatusBarUtil;
import com.wzj.wanandroid.R;
import com.wzj.wanandroid.ui.adapter.FragmentPagerAdapter;
import com.wzj.wanandroid.ui.chapter.ChapterFragment;
import com.wzj.wanandroid.ui.home.HomeFragment;
import com.wzj.wanandroid.ui.project.ProjectFragment;
import com.wzj.wanandroid.ui.tree.TreeFragment;

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
        List<Fragment> fragments = new ArrayList<>();
        HomeFragment homeFragment = new HomeFragment();
        TreeFragment treeFragment = new TreeFragment();
        ChapterFragment chapterFragment = new ChapterFragment();
        ProjectFragment projectFragment = new ProjectFragment();
        fragments.add(homeFragment);
        fragments.add(treeFragment);
        fragments.add(chapterFragment);
        fragments.add(projectFragment);

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(this, fragments);

        //当页面滑动的时候,相应的按钮改变状态
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            //在页面滚动时被调用
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            //在新的页面被选中时调用
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        rb_home.setChecked(true);
                        break;
                    case 1:
                        rb_system.setChecked(true);
                        break;
                    case 2:
                        rb_chapter.setChecked(true);
                        break;
                    case 3:
                        rb_project.setChecked(true);
                        break;
                }
            }

            //在页面滚动状态改变时被调用
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

        //点击radioButton切换fragment
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId){
                case R.id.rb_home:
                    rb_home.setChecked(true);
                    viewPager.setCurrentItem(0,true);
                    toolbar.setTitle("WanAndroid");
                    break;
                case R.id.rb_system:
                    rb_system.setChecked(true);
                    viewPager.setCurrentItem(1,true);
                    toolbar.setTitle("知识体系");
                    break;
                case R.id.rb_chapter:
                    rb_chapter.setChecked(true);
                    viewPager.setCurrentItem(2,true);
                    toolbar.setTitle("公众号");
                    break;
                case R.id.rb_project:
                    rb_project.setChecked(true);
                    viewPager.setCurrentItem(3,true);
                    toolbar.setTitle("项目");
                    break;
            }
        });
        viewPager.setAdapter(adapter);
        rb_home.setChecked(true);
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
        rb_home = findViewById(R.id.rb_home);
        rb_system = findViewById(R.id.rb_system);
        rb_chapter = findViewById(R.id.rb_chapter);
        rb_project = findViewById(R.id.rb_project);


        //设置Activity的Toolbar
        setSupportActionBar(toolbar);
        //启用返回按钮,在ActionBar的左侧显示一个箭头 点击它的时候,它将调用
        //onOptionsItemSelected方法并将传递给 android.R.id.home
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //监听器 ActionBarDrawerToggle是一个开关,用于打开/关闭DrawerLayout抽屉,它将Toolbar和DrawerLayout结合起来使用
        drawerToggle = new ActionBarDrawerToggle(this, drawer_layout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer_layout.setDrawerListener(drawerToggle);//设置监听器

        //将call菜单设置为默认选中
        navigation_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //处理菜单点击事件,当用户点击任意菜单的时候,都会回调到这里
                switch (item.getItemId()) {
                    case R.id.menu_favorite_article:
                        Toast.makeText(context, "喜欢的文章", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_about:
                        Toast.makeText(context, "关于我们", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_exit:
                        Toast.makeText(context, "退出登录", Toast.LENGTH_SHORT).show();
                        break;
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

    //创建toolbar右侧的menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    //处理toolbar右侧menu点击事件
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                if(drawer_layout.isDrawerOpen(GravityCompat.START)){
                    drawer_layout.closeDrawer(GravityCompat.START);
                }else{
                    drawer_layout.openDrawer(GravityCompat.START);
                }
                break;
            case R.id.menu_search:
                Toast.makeText(context, "点击了搜索", Toast.LENGTH_SHORT).show();
                break;
            default:break;
        }
        return true;
    }

    /**
     * 是一个生命周期方法,它会在onStart或onRestoreInstanceState之后被调用,与
     * onRestoreInstanceState只需要恢复状态时才被调用不同,onPostCreaet总是会被调用
     *
     * @param savedInstanceState
     */
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //在屏幕旋转的情况下同步ActionBarDrawerToggle的状态
        drawerToggle.syncState();
    }
}
