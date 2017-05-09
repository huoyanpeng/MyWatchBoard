package com.example.dell.androidview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * author: 霍彦朋 (dell) .
 * date: 2017/5/9.
 * function:
 */
public class MyTextView extends View {

    private String string;
    private int integer;

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.test);
        string = array.getString(R.styleable.test_text);
        integer = array.getInteger(R.styleable.test_textInt,-1);
        Log.e("!!!!!", "text = " + string + " , textAttr = " + integer);

//        array.recycle();
        //返回以前取回的属性，供以后使用。以前取回的可能就是textSize和textColor初始化的那段
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint(Color.BLUE);
//        canvas.drawText(string,20,20,20,20,paint);
        canvas.drawText("哎",0,0,50,50,paint);
    }
}
