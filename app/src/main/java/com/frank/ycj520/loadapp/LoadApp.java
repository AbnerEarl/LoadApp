package com.frank.ycj520.loadapp;

import android.content.Context;
import android.content.Intent;

import java.io.File;

public class LoadApp {
    public static void loadApp(Context context,File file){
        PluginManager.getInstance().setContext(context);
        //File file=new File(Environment.getExternalStorageDirectory(),"plugin.apk");
        PluginManager.getInstance().loadPath(file.getAbsolutePath());
        Intent intent=new Intent(context,ProxyActivity.class);
        intent.putExtra("className",PluginManager.getInstance().entryActivityName);
        context.startActivity(intent);
    }
}
