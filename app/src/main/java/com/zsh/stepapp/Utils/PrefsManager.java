package com.zsh.stepapp.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/*
管理sharedPreferences配置文件的工具类
 */
public class PrefsManager {
    private Context context;
    private static final String MYPREFS_NAME = "my_step";

    private PrefsManager(Context context) {
        this.context = context;
    }

    public static PrefsManager getInstance(Context context) {
        return new PrefsManager(context);
    }

    /*
    清除配置文件
     */
    public void clean() {
        context.getSharedPreferences(MYPREFS_NAME, Context.MODE_PRIVATE).edit().clear().commit();
    }

    /**
     * 判断配置文件是否存在
     * @return
     */
    public boolean contains()
    {
       return context.getSharedPreferences(MYPREFS_NAME, Context.MODE_PRIVATE).contains(MYPREFS_NAME);
    }



}

