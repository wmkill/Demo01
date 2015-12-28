package com.dever.qiubai;

import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class SlidingActivity extends AppCompatActivity implements SlidingPaneLayout.PanelSlideListener {
    private SlidingPaneLayout sliding;
    private FrameLayout content;
    private LinearLayout menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding);
        sliding = (SlidingPaneLayout) findViewById(R.id.sliding);
        menu = (LinearLayout) findViewById(R.id.menu);
        //sliding.closePane();//关闭
        //sliding.openPane();//打开
        content = (FrameLayout) findViewById(R.id.content);
        sliding.setPanelSlideListener(this);//滑动的监听

    }

    /**
     * 滑动中
     * @param panel 滑动的view
     * @param slideOffset [0-1]   滑动百分比 全部打开是1，全部关闭是0；
     */
    @Override
    public void onPanelSlide(View panel, float slideOffset) {
        //兼容版本
        ViewCompat.setPivotY(content,content.getHeight()/2);
        ViewCompat.setPivotX(content, 0);
        ViewCompat.setScaleX(content, 1 - slideOffset * 0.5f);
        ViewCompat.setScaleY(content, 1 - slideOffset * 0.5f);
        ViewCompat.setTranslationX(menu,menu.getHeight()/2*slideOffset);
        /*
        API 10以上才能用
        content.setPivotX(0);
        content.setScaleX(1-slideOffset*0.5f);
        content.setScaleY(1-slideOffset*0.5f);*/
    }

    /**
     * 打开后
     * @param panel
     */
    @Override
    public void onPanelOpened(View panel) {

    }

    /**
     * 关闭后
     * @param panel
     */
    @Override
    public void onPanelClosed(View panel) {

    }
}
