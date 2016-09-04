package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends FragmentActivity {

    @BindView(R.id.menu)
    slidingMenu mSlidingMenu;

    @BindView(R.id.tab_zhuye)
    LinearLayout tab_zhuye;
    @BindView(R.id.tab_timeline)
    LinearLayout tab_timeline;
    @BindView(R.id.tab_setting)
    LinearLayout tab_setting;

    @BindView(R.id.tab_zhuye_image)
    ImageButton tab_zhuye_image;
    @BindView(R.id.tab_timeline_image)
    ImageButton tab_timeline_image;
    @BindView(R.id.tab_setting_image)
    ImageButton tab_setting_image;

    private Fragment fragment_zhuye;
    private Fragment fragment_timeline;
    private Fragment fragment_setting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSelect(0);
    }

    //选择fragment
    //在使用replace的时候会执行fragment的所有生命周期
    //在使用add的时候一层fragment叠加在另一层fragment上
    private void setSelect(int i) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        switch (i) {
            case 0:

                if (fragment_zhuye == null) {
                    Log.i("ln", "执行select");
                    fragment_zhuye = new zhuye_fragment();
                    transaction.add(R.id.content, fragment_zhuye);
                } else {
                    transaction.show(fragment_zhuye);

                }
                break;
            case 1:
                if (fragment_timeline == null) {
                    fragment_timeline = new timeline_fragment();
                    transaction.add(R.id.content, fragment_timeline);
                } else {
                    transaction.show(fragment_timeline);
                }
                break;
            case 2:
                if (fragment_setting == null) {
                    fragment_setting = new setting_fragment();
                    transaction.add(R.id.content, fragment_setting);
                } else {
                    transaction.show(fragment_setting);
                }
                break;
        }
        transaction.commit();
    }

    //隐藏所有的fragment
    private void hideFragment(FragmentTransaction transaction) {
        if (fragment_zhuye != null) {
            transaction.hide(fragment_zhuye);
        }
        if (fragment_timeline != null) {
            transaction.hide(fragment_timeline);
        }
        if (fragment_setting != null) {
            transaction.hide(fragment_setting);
        }
    }

    //实现点击开关侧边栏
    public void toggleMenu(View view) {
        mSlidingMenu.toggle();
    }

    @OnClick(R.id.tab_zhuye)
    public void tab_zhuye() {
        setSelect(0);
    }

    @OnClick(R.id.tab_timeline)
    public void tab_timeline() {
        setSelect(1);
    }

    @OnClick(R.id.tab_setting)
    public void tab_setting() {
        setSelect(2);
    }

}
