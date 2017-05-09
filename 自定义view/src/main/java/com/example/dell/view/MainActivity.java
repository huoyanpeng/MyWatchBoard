package com.example.dell.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Neifangwaiyuan neifangwaiyuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        neifangwaiyuan = (Neifangwaiyuan) findViewById(R.id.ne);
        neifangwaiyuan.setOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "0000000000", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
