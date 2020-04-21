package com.wd.tech.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/**
 * date:2020/4/17 0017
 * author:王黎杨
 * function:
 */
public class ScroMenu extends HorizontalScrollView {
    //滚动条中的水平先行布局
    private LinearLayout mWrpper;
    //水平线性布局的左侧菜单menu
    private ViewGroup mMenu;
    //水平先行布局的右侧布局
    private ViewGroup mContent;
    //屏幕的宽
    private int mScreenWidth;
    //menu的宽离屏幕右侧的距离
    private int mMenuRightPadding=100;
    //menu的宽度
    private int mMenuWidth;
    private boolean once;

    public ScroMenu(Context context) {
        super(context);
    }

    public ScroMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        /*
         * 获取屏幕的宽度
         * 通过context拿到windowManager，在通过windowManager拿到Metrics,用DisplayMetrics接收
         * */
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        //屏幕宽度
        mScreenWidth=outMetrics.widthPixels;
        //把dp转换成px
        mMenuRightPadding=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50,
                context.getResources().getDisplayMetrics());
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(!once){
            mWrpper=(LinearLayout) getChildAt(0);
            //菜单
            mMenu=(ViewGroup) mWrpper.getChildAt(0);
            //内容
            mContent=(ViewGroup) mWrpper.getChildAt(1);
            //menu的宽度等于屏幕的宽度减去menu离屏幕右侧的边距
            mMenuWidth=mMenu.getLayoutParams().width=mScreenWidth-mMenuRightPadding;
            //右边的先行布局的宽度直接等于屏幕的宽度
            mContent.getLayoutParams().width=mScreenWidth;
            once=true;
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // TODO Auto-generated method stub
        super.onLayout(changed, l, t, r, b);
         // 通过scrollTo（x,y）方法设置屏幕的偏移量，x为正
        if(changed){
            this.scrollTo(mMenuWidth, 0);
        }


    }
    /*
     * 因为HorizontalScrollView自己控制move和down的事件
     * 所以我们还要判断一下up.如果当前的x偏移量大于menu宽度的一半
     * 隐藏menu，否则显示menu
     * */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        switch(ev.getAction()){
            case MotionEvent.ACTION_UP:
                int scrollX=getScrollX();
                if(scrollX>=mMenuWidth/2){
                    this.smoothScrollTo(mMenuWidth, 0);
                } else{
                    this.smoothScrollTo(0, 0);
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }

}
