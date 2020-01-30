package com.zsh.stepapp.Utils;

import android.content.Context;

import androidx.annotation.NonNull;

import com.zsh.stepapp.StepApplication;

public class ErrorHandler implements Thread.UncaughtExceptionHandler {
    private static ErrorHandler instance;

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        LogWriter.LogToFile(true,"Error","崩溃信息:"+e.getMessage());
        LogWriter.LogToFile(true,"Error","崩溃线程名称:"+t.getName()+" 线程ID："+t.getId());
        StackTraceElement[] trace=e.getStackTrace();
        for (StackTraceElement element:trace)
        {
            LogWriter.LogToFile(true,"Line :"+element.getLineNumber()+":",element.getMethodName());
        }
        e.printStackTrace();
        StepApplication.exitApp();
    }

    private ErrorHandler() {

    }

    public static ErrorHandler getInstance() {
        if (instance == null) {
            instance = new ErrorHandler();
        }
        return instance;
    }

    public void setErrorHandler(Context context)
    {
        Thread.setDefaultUncaughtExceptionHandler(this);
    }
}
