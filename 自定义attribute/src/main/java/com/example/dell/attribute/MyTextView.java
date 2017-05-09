package com.example.dell.attribute;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import static android.content.ContentValues.TAG;

/**
 * author: 霍彦朋 (dell) .
 * date: 2017/5/9.
 * function:
 */
public class MyTextView extends View {
    private static final String TAG = MyTextView.class.getSimpleName();
    private String text;
    private Paint mPaint ;
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.test);

        text = ta.getString(R.styleable.test_text);
        int textAttr = ta.getInteger(R.styleable.test_testAttr, -1);

        Log.e(TAG, "text = " + text + " , textAttr = " + textAttr);

//        ta.recycle();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#FF4081"));
        mPaint.setTextSize(90f);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(text, 0 ,getHeight()/2 ,mPaint);
    }
    /**
     * 测量
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int wSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int wSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int hSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int hSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if (wSpecMode == MeasureSpec.AT_MOST && hSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(300,300);
        }else  if (wSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(300,hSpecSize);
        }else if (hSpecMode ==  MeasureSpec.AT_MOST) {
            setMeasuredDimension(wSpecSize,300);
        }
    }
}
