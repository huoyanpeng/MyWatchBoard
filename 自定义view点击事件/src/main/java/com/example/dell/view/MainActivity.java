package com.example.dell.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    private MySlider mMySilder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.textview);
        mMySilder = (MySlider) findViewById(R.id.mysilider);
        mMySilder.setOnItemSelectListener(new MySlider.OnItemSelectListener() {
            @Override
            public void onItemSelect(int index, String indexString) {

                mTextView.setText(indexString);
            }
        });

    }
}
