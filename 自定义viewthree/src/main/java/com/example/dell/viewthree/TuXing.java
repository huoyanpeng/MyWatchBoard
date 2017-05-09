package com.example.dell.viewthree;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import static android.R.attr.path;
import static android.graphics.Path.Op.DIFFERENCE;

/**
 * author: 霍彦朋 (dell) .
 * date: 2017/5/8.
 * function:
 */
public class TuXing extends View {
    private Paint paint;
    private Region region01;
    private Region region02;

    public TuXing(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TuXing(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        paint =new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(3);
    }

    public TuXing(Context context) {
        super(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth()/2;
        int height = getHeight()/2;
        Path path01=new Path();
        path01.addCircle(width,height,width,Path.Direction.CCW);
        canvas.drawPath(path01, paint);

        Path path02=new Path();
        //绘制矩形的起点
        path02.moveTo(width,height-width);
        //从点（0,0）到点（100,0）绘制一条线
        path02.lineTo(width*2,height);
        //从点（100,0）到点（100,100）绘制一条线
        path02.lineTo(width,height+width);
        path02.lineTo(0,height);
        path02.lineTo(width,height-width);
        canvas.drawPath(path02, paint);

        /**
         * 绘制正方形
         */
        //初始化画笔
        Path path03=new Path();
        //设置起始位置
        path03.moveTo(width/2,height-width/2);
        path03.lineTo(width*2-width/2,height-width/2);
        path03.lineTo(width*2-width/2,height+width/2);
        path03.lineTo(width/2,height+width/2);
        path03.lineTo(width/2,height-width/2);
        canvas.drawPath(path03,paint);

        path01.op(path02,DIFFERENCE);
        path02.op(path03,DIFFERENCE);
        //quyv
        region01 = new Region();
        region02 = new Region();
        region01.setPath(path01, new Region(0, 0, 1000, 1000));
        region02.setPath(path02, new Region(0, 0, 1000, 1000));
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int  x = (int) event.getX();
        int  y= (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:

                break;
        }

        return super.dispatchTouchEvent(event);
    }
}
