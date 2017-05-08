package com.example.dell.mywatchboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * author: 霍彦朋 (dell) .
 * date: 2017/5/6.
 * function:
 */
public class WatchBoard extends View {

    private int yuanWidch;
    private int yuanHeight;
    public WatchBoard(Context context) {
        super(context);
    }

    public WatchBoard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public WatchBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取屏幕的高宽
        WindowManager systemService = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        yuanHeight=systemService.getDefaultDisplay().getHeight();
        yuanWidch=systemService.getDefaultDisplay().getWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 绘制圆
         */
        //初始化画笔
        Paint yuanPaint=new Paint();
        //设置颜色
        yuanPaint.setColor(Color.BLACK);
        //设置抗锯齿
        yuanPaint.setAntiAlias(true);
        //设置实心圆
        yuanPaint.setStyle(Paint.Style.FILL);
        //画笔宽度10
        yuanPaint.setStrokeWidth(10);

        canvas.drawCircle(yuanWidch/2,yuanHeight/2,yuanWidch/2,yuanPaint);

        /**
         * 绘制菱形
         */

        //设置颜色
        Paint lingPaint=new Paint();
        Path path=new Path();
        lingPaint.setColor(Color.RED);
        //设置抗锯齿
        lingPaint.setAntiAlias(true);
        lingPaint.setStyle(Paint.Style.FILL);
        //绘制矩形的起点
        path.moveTo(yuanWidch/2,yuanHeight/2-yuanWidch/2);
        //从点（0,0）到点（100,0）绘制一条线
        path.lineTo(yuanWidch,yuanHeight/2);
        //从点（100,0）到点（100,100）绘制一条线
        path.lineTo(yuanWidch/2,yuanHeight/2+yuanWidch/2);
        path.lineTo(0,yuanHeight/2);
        path.lineTo(yuanWidch/2,yuanHeight/2-yuanWidch/2);
        canvas.drawPath(path, lingPaint);

        /**
         * 绘制正方形
         */
        //初始化画笔
        Paint zhengPaint=new Paint();
        Path zhengPath=new Path();
        //设置颜色
        zhengPaint.setColor(Color.BLUE);
        //抗锯齿
        zhengPaint.setAntiAlias(true);
        //设置是实体
        zhengPaint.setStyle(Paint.Style.FILL);
        //设置起始位置
        zhengPath.moveTo(yuanWidch/4,yuanHeight/2-yuanWidch/4);
        zhengPath.lineTo(yuanWidch-yuanWidch/4,yuanHeight/2-yuanWidch/4);
        zhengPath.lineTo(yuanWidch-yuanWidch/4,yuanHeight/2+yuanWidch/4);
        zhengPath.lineTo(yuanWidch/4,yuanHeight/2+yuanWidch/4);
        zhengPath.lineTo(yuanWidch/4,yuanHeight/2-yuanWidch/4);
        canvas.drawPath(zhengPath,zhengPaint);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int  x = (int) event.getX();
        int  y= (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
//                if (x <= mWidth / 4 + mWidth / 2 && x >= mWidth / 4 && y <= mHeight / 2 + mWidth / 4 && y >= mHeight / 2 - mWidth / 4) {
//                    Toast.makeText(getContext(), "我是黄小方", Toast.LENGTH_SHORT).show();
//                } else if ((x < mWidth / 2 && x > 0 && Math.abs(y - mHeight / 2) < x)||(x > mWidth / 2 && x < mWidth && Math.abs(y - mHeight / 2) < mWidth-x)) {
//                    Toast.makeText(getContext(), "我是绿小方", Toast.LENGTH_SHORT).show();
//                }
                if (x>yuanWidch/2&&x<yuanWidch-yuanWidch/4&&y>yuanHeight/2-yuanWidch/4&&y<yuanHeight/2+yuanWidch/4){
                    Toast.makeText(getContext(), "我是正方形", Toast.LENGTH_SHORT).show();
                }else if (x<yuanWidch/2&&x>0&&Math.abs(y-yuanHeight/2)<x){

                }

                break;
        }

        return super.dispatchTouchEvent(event);
    }
}
