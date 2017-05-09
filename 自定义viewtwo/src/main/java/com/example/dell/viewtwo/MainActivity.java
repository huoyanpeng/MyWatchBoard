package com.example.dell.viewtwo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{
    private YuanFang yf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        yf = (YuanFang) findViewById(R.id.yf);

        yf.setOnTouchListener(this);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:


                //圆方

                //圆心坐标
                int lastX =  yf.getWidth() / 2;
                int lastY =  yf.getHeight() / 2;

                Log.i("zzz","x  y " + x +"  "+ y + " last x y " + lastX + " " + lastY );

                //圆半径 通过左右坐标计算获得getLeft
                int r = (yf.getRight()-yf.getLeft())/2-5;

                //点击位置x坐标与圆心的x坐标的距离
                int distanceX = Math.abs((int)x-lastX);
                //点击位置y坐标与圆心的y坐标的距离
                int distanceY = Math.abs((int)y-lastY);
                //点击位置与圆心的直线距离
                int distanceZ = (int) Math.sqrt(Math.pow(distanceX,2)+Math.pow(distanceY,2));

                //如果点击位置与圆心的距离小于圆的半径，证明点击位置在圆内
                if(distanceZ < r){
                    float x1 = x - lastX;
                    float y1 = (float) Math.floor((double) (y - lastY));
                    if( r-Math.abs(x1) > Math.abs(y1)){

                        if (x>lastX-r/2 && x< lastX+r/2 && y >lastY-r /2&& y<lastY+r/2){
                            Toast.makeText(MainActivity.this,"在方内",Toast.LENGTH_SHORT).show();
                            return true;
                        }
                        Toast.makeText(MainActivity.this,"在菱形内",Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    Toast.makeText(MainActivity.this,"在圆内",Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return true;

    }
}
