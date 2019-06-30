package com.hh.gridview_recyclerview.View;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Scroller;

/**
 * Created by 徐小鹏 on 2017/2/10.
 * <p>
 * 注释：
 */

public class SlideView extends ViewGroup {
    private float downX; // 按下的x坐标
    private float downY; // 按下的y坐标
    private float moveX;
    public static final int MAIN_STATE = 0;
    public static final int MENU_STATE = 1;
    private int currentState = MAIN_STATE; // 当前模式
    private Scroller scroller;

    public SlideView(Context context) {
        super(context);
        init();
    }

    public SlideView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SlideView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        // 初始化滚动器, 数值模拟器
        scroller = new Scroller(getContext());
    }

    /**
     * 测量并设置 所有子View的宽高
     * widthMeasureSpec: 当前控件的宽度测量规则
     * heightMeasureSpec: 当前控件的高度测量规则
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        // 指定左面板的宽高
        View leftMenu = getChildAt(0);
        leftMenu.measure(leftMenu.getMeasuredWidth(), heightMeasureSpec);

        // 指定主面板的宽高
        View mainContent = getChildAt(1);
        mainContent.measure(widthMeasureSpec, heightMeasureSpec);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * changed: 当前控件的尺寸大小, 位置 是否发生了变化
     * left:当前控件 左边距
     * top:当前控件 顶边距
     * right:当前控件 右边界
     * bottom:当前控件 下边界
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        // 摆放内容, 左面板
        View leftMenu = getChildAt(0);
        leftMenu.layout(-leftMenu.getMeasuredWidth(), 0, 0, b);

        // 主面板
        getChildAt(1).layout(l, t, r, b);
    }

    /**
     * 处理触摸事件
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                moveX = event.getX();

                // 将要发生的偏移量/变化量
                int scrollX = (int) (downX - moveX);

                // 计算将要滚动到的位置, 判断是否会超出去, 超出去了.不执行scrollBy
                // getScrollX() 当前滚动到的位置
                int newScrollPosition = getScrollX() + scrollX;

                if (newScrollPosition < -getChildAt(0).getMeasuredWidth()){ // 限定左边界
                    // < -240
                    scrollTo(-getChildAt(0).getMeasuredWidth(), 0);
                } else if (newScrollPosition > 0){ // 限定右边界
                    // > 0
                    scrollTo(0, 0);
                } else {
                    // 让变化量生效
                    scrollBy(scrollX, 0);
                }

                downX = moveX;

                break;
            case MotionEvent.ACTION_UP:
                // 根据当前滚动到的位置, 和左面板的一半进行比较
                int leftCenter = (int) (- getChildAt(0).getMeasuredWidth() / 2.0f);

                if(getScrollX() < leftCenter){
                    // 打开, 切换成菜单面板
                    currentState = MENU_STATE;
                    updateCurrentContent();
                }else {
                    // 关闭, 切换成主面板
                    currentState = MAIN_STATE;
                    updateCurrentContent();
                }

                break;
            default:
                break;
        }

        return true; // 消费事件
    }

    /**
     * 根据当前的状态, 执行 关闭/开启 的动画
     */
    private void updateCurrentContent() {
        int startX = getScrollX();
        int dx = 0;
        // 平滑滚动
        if(currentState == MENU_STATE){
            // 打开菜单
//			scrollTo(-getChildAt(0).getMeasuredWidth(), 0);
//			dx = 结束位置(-240) - 开始位置(-200) = -40
//			dx = 0 - (-10) = 10

            dx = -getChildAt(0).getMeasuredWidth() - startX;

        } else {
            // 恢复主界面
//			scrollTo(0, 0);

            dx = 0 - startX;
        }

        // startX: 开始的x值
        // startY: 开始的y值
        // dx: 将要发生的水平变化量. 移动的x距离
        // dy: 将要发生的竖直变化量. 移动的y距离
        // duration : 数据模拟持续的时长

        // 1. 开始平滑的数据模拟
        int duration = Math.abs(dx * 2); // 0 -> 1200
        scroller.startScroll(startX, 0, dx, 0, duration);

//		- 200 -> -240
//		- 201 -> -202 -> -203 .... -236 -> -240

        invalidate();// 重绘界面 -> drawChild() -> computeScroll();
    }

    //2. 维持动画的继续
    @Override
    public void computeScroll() {
        super.computeScroll();
        if(scroller.computeScrollOffset()){ // 直到duration事件以后, 结束
            // true, 动画还没有结束
            // 获取当前模拟的数据, 也就是要滚动到的位置
            int currX = scroller.getCurrX();
            System.out.println("currX: " + currX);
            scrollTo(currX, 0); // 滚过去

            invalidate(); // 重绘界面-> drawChild() -> computeScroll();循环
        }
    }

    public void open(){
        currentState = MENU_STATE;
        updateCurrentContent();
    }
    public void close(){
        currentState = MAIN_STATE;
        updateCurrentContent();
    }

    public void switchState(){
        if(currentState == MAIN_STATE){
            open();
        }else {
            close();
        }
    }

    public int getCurrentState(){
        return currentState;
    }


    /**
     * 拦截判断
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = ev.getX();
                downY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:

                float xOffset = Math.abs(ev.getX() - downX);
                float yOffset = Math.abs(ev.getY() - downY);

                if(xOffset > yOffset && xOffset > 5){ // 水平方向超出一定距离时,才拦截
                    return true; // 拦截此次触摸事件, 界面的滚动
                }

                break;
            case MotionEvent.ACTION_UP:

                break;

            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
