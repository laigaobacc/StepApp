package com.zsh.stepapp.Activity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.zsh.stepapp.StepApplication;

public abstract class BaseActivity extends FragmentActivity {
    private boolean isHideAppTitle = true;
    private boolean isHideSystemTitle = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        if (this.isHideAppTitle) {
            this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        if (this.isHideSystemTitle) {
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        super.onCreate(savedInstanceState);
        this.InitVariable();
        this.InitView(savedInstanceState);
        this.onRequestsData();
    }

    @Override
    protected void onDestroy() {
        StepApplication.removeFromActivity(this);
        super.onDestroy();
    }

    /*
        初始化变量
         */
    public abstract void InitVariable();

    /*
    初始化UI
     */
    public abstract void InitView(Bundle savedInstanceState);

    /*
     请求数据
     */
    public abstract void onRequestsData();
}
