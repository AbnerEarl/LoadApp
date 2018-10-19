package com.frank.ycj520.loadapp;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class PluginManager {
    public static String entryActivityName;
    private Context context;
    private DexClassLoader dexClassLoader;
    private Resources resources;
    private static final PluginManager ourInstance=new PluginManager();
    public static PluginManager getInstance(){
        return ourInstance;
    }
    public PluginManager(){

    }

    public void loadPath(String path){

        PackageManager packageManager=context.getPackageManager();
        PackageInfo packageInfo = packageManager.getPackageArchiveInfo(path,PackageManager.GET_ACTIVITIES);
        entryActivityName=packageInfo.activities[0].name;

        File dexOutFile=context.getDir("dex",Context.MODE_PRIVATE);
        dexClassLoader=new DexClassLoader(path,dexOutFile.getAbsolutePath(),null,context.getClassLoader());

        try {
            AssetManager assetManager=AssetManager.class.newInstance();
            Method addAssetPath=AssetManager.class.getMethod("addAssetPath",String.class);
            addAssetPath.invoke(assetManager,path);
            resources=new Resources(assetManager,context.getResources().getDisplayMetrics(),context.getResources().getConfiguration());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public DexClassLoader getDexClassLoader() {
        return dexClassLoader;
    }

    public Resources getResources() {
        return resources;
    }

    public String getEntryActivityName() {
        return entryActivityName;
    }

    public void setContext(Context context) {
        this.context = context.getApplicationContext();
    }
}
