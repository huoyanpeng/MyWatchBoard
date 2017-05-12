package com.example.dell.recyclerviewdemo;

import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private String vpath="http://result.eolinker.com/gfGTLlHc049c6b450500b16971f52bd8e83f6b2fed305ab?uri=news";
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String str= (String) msg.obj;

            Gson gson=new Gson();
            Bean bean = gson.fromJson(str, Bean.class);
            List<Bean.ResultBean.DateBean> date = bean.getResult().getDate();

            MyRecyclerView myRecyclerView=new MyRecyclerView(date,MainActivity.this);
            recyclerView.setAdapter(myRecyclerView);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        final OkHttpClient mClient=new OkHttpClient();
        final Request requ=new Request.Builder()
                .get()
                .url(vpath)
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response execute = mClient.newCall(requ).execute();
                    if (execute.isSuccessful()){
                        String string = execute.body().string();
                        Message message=new Message();
                        message.obj=string;
                        handler.sendMessage(message);
                        execute.body().close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        manager = new LinearLayoutManager(this);
        //设置布局管理器
        recyclerView.setLayoutManager(manager);
        //设置为垂直布局
        manager.setOrientation(OrientationHelper.VERTICAL);
    }
}
