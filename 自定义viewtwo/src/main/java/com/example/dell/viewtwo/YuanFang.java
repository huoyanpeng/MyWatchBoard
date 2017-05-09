package com.example.dell.viewtwo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * author: 霍彦朋 (dell) .
 * date: 2017/5/8.
 * function:
 */
public class YuanFang extends View {


    private float mR;
    private float mY;
    private float mX;
    private static final float DEFAULT_BORDER_WIDTH = 10f; //外圆 边宽
    private int[] mInts;


    public YuanFang(Context context) {
        super(context);
    }

    public YuanFang(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public YuanFang(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        reset();

        //画圆

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE); // 空心圆
        paint.setAntiAlias(true); //抗锯齿
        paint.setStrokeWidth(DEFAULT_BORDER_WIDTH);
        canvas.drawCircle(mX,mY,mR,paint);

        //画方
        Rect rect = new Rect((int)(mX-mInts[0]),(int)(mY-mInts[1]),(int)(mX+mInts[0]),(int)(mY+mInts[1]));
        canvas.save();
        canvas.rotate(45,mX,mY);  //先画 后转
        canvas.drawRect(rect,paint);
        canvas.restore();   //回滚保存状态画布

        //画内方
        Rect rect2 = new Rect( (int)(mX- mR / 2+DEFAULT_BORDER_WIDTH ) ,(int)(mY-mR / 2+DEFAULT_BORDER_WIDTH  ),(int)(mX + mR / 2 -DEFAULT_BORDER_WIDTH / 2),(int)(mY+mR / 2-DEFAULT_BORDER_WIDTH / 2) );
        canvas.drawRect(rect2,paint);


    }

    private void reset(){
        //半径
        mR = (Math.min(getHeight() / 2, getWidth() / 2)-DEFAULT_BORDER_WIDTH / 2);
        //圆心
        mY = getHeight()/2;
        mX = getWidth()/2;

        //正方形的四个点 （正）
        mInts = new int[4];
        mInts[0] = (int)(Math.sin(45*Math.PI/180) * mR-DEFAULT_BORDER_WIDTH / 2);  //圆内的x
        mInts[1] =  (int)(Math.cos(45*Math.PI/180) * mR-DEFAULT_BORDER_WIDTH / 2);  //圆内的y

    }


}
