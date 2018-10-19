package com.frank.ycj520.loadapp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import com.frank.ycj520.standard.StandardInterface;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ProxyActivity extends Activity {
    private String className;
    StandardInterface standardInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_proxy);
        className=getIntent().getStringExtra("className");
        try {
            Class activityClass=getClassLoader().loadClass(className);
            Constructor constructor=activityClass.getConstructor(new Class[]{});
            Object instance=constructor.newInstance(new Object[]{});
            standardInterface=(StandardInterface) instance;
            standardInterface.attach(this);
            //传递数据
            Bundle bundle=new Bundle();
            standardInterface.onCreate(bundle);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        standardInterface.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        standardInterface.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        standardInterface.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        standardInterface.onStop();
    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance().getDexClassLoader();
    }

    @Override
    public Resources getResources() {
        return PluginManager.getInstance().getResources();
    }


    @Override
    public void startActivity(Intent intent) {
        String classNameFromTaoPiaoPiao=intent.getStringExtra("className");
        Intent destanceIntent=new Intent(this,ProxyActivity.class);
        destanceIntent.putExtra("className",classNameFromTaoPiaoPiao);
        super.startActivity(destanceIntent);
    }
}
