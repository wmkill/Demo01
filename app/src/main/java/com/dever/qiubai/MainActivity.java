package com.dever.qiubai;

import android.app.Activity;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private NavigationView menu;
    private ActionBarDrawerToggle toggle;
    private ViewPager pager;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = (DrawerLayout) findViewById(R.id.drawer);
        menu = (NavigationView) findViewById(R.id.menu);
        menu.setNavigationItemSelectedListener(this);
        //menu.setOnClickListener(this);

        toggle = new ActionBarDrawerToggle(this, drawer, 0, 0);//显示三条横线
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//显示home按钮
        toggle.syncState();
        //有DrawerLayout控制toggle
        drawer.setDrawerListener(toggle);//让drawerLayout的动作影响上边的导航按钮

        pager = (ViewPager) findViewById(R.id.pager);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(String.format("第%02d页",i));
        }
        adapter = new MyAdapter(getSupportFragmentManager(), list);
        pager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(pager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);//这个方法一定要在adapter之后调用
    }

    /**
     * 监听menu
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //toggle控制DrawerLayout
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_1:
                Toast.makeText(this,"1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.group_3:
                ActivityCompat.finishAffinity(this);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /*@Override
    public void onClick(View v) {
        drawer.closeDrawer(GravityCompat.START);//关闭侧滑菜单  Gravity.START（4.0以下不支持，需要兼容包）确定关闭的方位  GravityCompat.START 是兼容写法
    }*/
}
