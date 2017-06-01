package com.example.dell.okhttpdome;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button geta;
    private Button getb;
    private Button posta;
    private Button postb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        geta = (Button) findViewById(R.id.geta);
        getb = (Button) findViewById(R.id.getb);
        posta = (Button) findViewById(R.id.posta);
        postb = (Button) findViewById(R.id.postb);

        geta.setOnClickListener(this);
        getb.setOnClickListener(this);
        posta.setOnClickListener(this);
        postb.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.geta:
                getOkHttpA();
                break;
            case R.id.getb:
                getOkHttpB();
                break;
            case R.id.posta:
                postOkHttpA();
                break;
            case R.id.postb:
                postOkHttpB();
                break;
        }
    }

    /**
     * OkHttp:get同步请求
     */
    public void getOkHttpA() {
        final OkHttpClient mClient =new OkHttpClient();
        final Request requ=new Request.Builder()
                .get()
                .url("http://result.eolinker.com/gfGTLlHc049c6b450500b16971f52bd8e83f6b2fed305ab?uri=news")
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Response execute = mClient.newCall(requ).execute();
                    if (execute!=null){
                        final String s = execute.body().string();
                        Log.e("get同步","get同步==========="+s);
                        execute.body().close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    /**
     * OkHttp:get异步请求
     */
    public void getOkHttpB() {
        final OkHttpClient mClient=new OkHttpClient();
        final Request request=new Request.Builder()
                .get()
                .url("http://result.eolinker.com/gfGTLlHc049c6b450500b16971f52bd8e83f6b2fed305ab?uri=news")
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Call call = mClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("get异步","请求失败"+e.toString());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.e("get异步","请求成功"+response.body().string());
                        response.body().close();
                    }
                });
            }
        }).start();

    }
    /**
     * OkHttp:post同步请求
     * http://admin.wap.china.com/user/NavigateTypeAction.do?processID=getNavigateNews
     */
    public void postOkHttpA() {
        final OkHttpClient mClient=new OkHttpClient();

        new Thread(new Runnable() {
            @Override
            public void run() {

                RequestBody requestBody=new FormBody.Builder()
                        .add("processID","getNavigateNews")
                        .build();
                Request request=new Request.Builder()
                        .url("http://admin.wap.china.com/user/NavigateTypeAction.do")
                        .post(requestBody)
                        .build();
                Call call = mClient.newCall(request);
                try {
                    Response execute = call.execute();
                    if (execute!=null){
                        Log.e("post同步","post同步:"+execute.body().string());
                        execute.body().close();
                    }else {
                        throw new IOException("post同步" + execute);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    /**
     * OkHttp:get异步请求
     */
    public void postOkHttpB() {

        final OkHttpClient mClient=new OkHttpClient();
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestBody requestBody=new FormBody.Builder()
                        .add("processID","getNavigateNews")
                        .build();
                Request request=new Request.Builder()
                        .url("http://admin.wap.china.com/user/NavigateTypeAction.do")
                        .post(requestBody)
                        .build();
                mClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("post异步","post异步"+e.toString());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.e("post异步","post异步"+response.body().string());
                        response.body().close();
                    }
                });

            }
        }).start();

    }
}
