package com.frank.ycj520.loadapp;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PluginManager.getInstance().setContext(this);

        findViewById(R.id.btn_load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    File file = new File(Environment.getExternalStorageDirectory(), "develop.apk");
                    if (file == null || file.getAbsolutePath() == null || file.getAbsolutePath() == ""){
                        Toast.makeText(MainActivity.this,"请检查手机存储卡的根目录是否有：develop.apk 这个app文件。",Toast.LENGTH_LONG).show();
                        return;
                    }
                    PluginManager.getInstance().loadPath(file.getAbsolutePath());
                    Intent intent = new Intent(MainActivity.this, ProxyActivity.class);
                    intent.putExtra("className", PluginManager.getInstance().entryActivityName);
                    startActivity(intent);
                }catch (Exception e){
                    Toast.makeText(MainActivity.this,"请检查手机存储卡的根目录是否有：develop.apk 这个app文件。",Toast.LENGTH_LONG).show();
                }
            }
        });

        findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ProxyActivity.class);
                intent.putExtra("className",PluginManager.getInstance().entryActivityName);
                startActivity(intent);
            }
        });
    }

}
