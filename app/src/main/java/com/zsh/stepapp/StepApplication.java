package com.zsh.stepapp;

import android.app.Activity;
import android.app.Application;

import com.zsh.stepapp.Utils.ErrorHandler;
import com.zsh.stepapp.Utils.PrefsManager;

import java.util.LinkedList;

public class StepApplication extends Application {

    private static StepApplication stepApplication;
    private StepApplication()
    {

    }
    public static StepApplication getInstance()
    {
        if(stepApplication!=null)
        {
            stepApplication=new StepApplication();
        }
        return stepApplication;
    }

    private static LinkedList<Activity> activityLinkedList = new LinkedList<>();


    //添加Activity
    public static void addtoActivityList(Activity activity)
    {
        if(activity!=null) {
            activityLinkedList.add(activity);
        }
    }

    //删除Activity
    public static void removeFromActivity(Activity activity){
        if(activityLinkedList!=null&&activityLinkedList.size()>0&&activityLinkedList.indexOf(activity)!=-1)
        {
            activityLinkedList.remove(activity);
        }
    }

    //清理所有Activity
    public static void cleanActivity()
    {
        activityLinkedList.clear();
    }

    //退出App
    public static void exitApp(){
        cleanActivity();
        System.exit(0);
        android.os.Process.killProcess(android.os.Process.myPid());
    }
    private PrefsManager prefsManager;
    private ErrorHandler errorHandler;
    public PrefsManager getPrefsManager(){
        return  prefsManager;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        prefsManager=PrefsManager.getInstance(this);
        errorHandler=ErrorHandler.getInstance();
    }
}
