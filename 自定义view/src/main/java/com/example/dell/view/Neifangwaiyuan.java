package com.example.dell.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import static android.graphics.Path.Op.DIFFERENCE;


/**
 * author: 霍彦朋 (dell) .
 * dat: 2017/5/8.
 * function:
 */
public class Neifangwaiyuan extends View {

    private Paint mPaint;
    private Region mRegion;
    private Region mRegion1;

    public Neifangwaiyuan(Context context) {
        super(context);
    }

    public Neifangwaiyuan(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);
        mPaint.setColor(Color.RED);
    }

    public Neifangwaiyuan(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int x = getWidth() / 2;
        int y = getHeight() / 2;
        Path path0 = new Path();
        path0.addCircle(x, y, 200, Path.Direction.CCW);
        canvas.drawPath(path0, mPaint);

        Path path = new Path();
        path.moveTo(x - 200, y);
        path.lineTo(x, y - 200);
        path.lineTo(x + 200, y);
        path.lineTo(x, y + 200);
        path.lineTo(x - 200, y);
        path.close();
        canvas.drawPath(path, mPaint);

        Path path2 = new Path();
        path2.moveTo(x - 100, y - 100);
        path2.lineTo(x + 100, y - 100);
        path2.lineTo(x + 100, y + 100);
        path2.lineTo(x - 100, y + 100);
        path2.lineTo(x - 100, y - 100);

        canvas.drawPath(path2, mPaint);

        path0.op(path, DIFFERENCE);
        path.op(path2, DIFFERENCE);

        mRegion = new Region();
        mRegion1 = new Region();
        mRegion.setPath(path0, new Region(0, 0, getWidth(), getHeight()));
        mRegion1.setPath(path, new Region(0, 0, getWidth(), getHeight()));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int w = (int) event.getX();
        int h = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                if (mRegion.contains(w, h)) {
                    if (mListener!=null) {
                        mListener.onClick(this);
                    }

                }
                if (mRegion1.contains(w, h)) {
                    if (m!=null){
                        m.onClick(this);
                    }
                }
                break;
        }
        return true;
    }


    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(l);
        mListener = l;
    }

    public void setOnClick(OnClickListener m) {
        this.m = m;
    }

    OnClickListener mListener;
    OnClickListener m;

}
