package com.example.administrator.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2016/8/30.
 */
public class slidingMenu extends HorizontalScrollView {

    private LinearLayout mwapper;
    //侧滑菜单
    private ViewGroup mMenu;
    //主体内容
    private ViewGroup mContent;
    //屏幕宽度
    private int mScreenWidth;
    //侧滑菜单的宽度
    private int mMenuWidth;
    //单位为dp
    private int mMenuRightPadding = 50;

    private boolean once = false;

    private boolean isOpen = false;

    private boolean isClose = true;

    /**
     * 当使用自定义属性时调用此方法
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public slidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 未自定义属性时调用
     *
     * @param context
     * @param attrs
     */
    public slidingMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取屏幕的宽度
        WindowManager wm = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        mScreenWidth = metrics.widthPixels;
        //把dp转换为px
        mMenuRightPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, context.getResources().getDisplayMetrics());
    }

    /**
     * 设置子view的宽和高
     * 设置自己的宽和高
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        if (!once) {
            mwapper = (LinearLayout) getChildAt(0);
            mMenu = (ViewGroup) mwapper.getChildAt(0);
            mContent = (ViewGroup) mwapper.getChildAt(1);
            //设置菜单的宽度和主内容区域的宽度
            mMenuWidth = mMenu.getLayoutParams().width = mScreenWidth - mMenuRightPadding;
            mContent.getLayoutParams().width = mScreenWidth;
            once = true;
        }


        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 通过设置偏移量将menu隐藏
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        if (changed) {
            //x为正值的时候滚动条像左移动，内容区域象右移动
            this.scrollTo(mMenuWidth, 0);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_UP:
                //scroolX是隐藏在左边的宽度
                int scroolX = getScrollX();
                if (scroolX >= mMenuWidth / 2) {
                    //smoothScrollTo对比与scrollto的区别在于smoothScrollTo能看到移动轨迹
                    this.smoothScrollTo(mMenuWidth, 0);
                    isOpen = false;
                    isClose = true;
                } else {
                    this.smoothScrollTo(0, 0);
                    isOpen = true;
                    isClose = false;
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }

    //实现点击开启侧滑菜单
    public void openMenu() {
        if (isOpen) {
            return;
        } else {
            this.smoothScrollTo(0, 0);
            isOpen = true;
            isClose = false;
        }
    }

    //实现点击关闭菜单
    public void closeMenu() {
        if (isClose) {
            return;
        } else {
            this.smoothScrollTo(mMenuWidth, 0);
            isClose = true;
            isOpen = false;
        }
    }

    //切换菜单
    public void toggle() {
        if (isOpen) {
            closeMenu();
        } else {
            openMenu();
        }
    }

}
