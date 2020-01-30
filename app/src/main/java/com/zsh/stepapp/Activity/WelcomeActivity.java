package com.zsh.stepapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.zsh.stepapp.R;

public class WelcomeActivity extends BaseActivity {
    private Handler handler;
    private Runnable jumpRunable;
    @Override
    public void InitVariable() {
        handler=new Handler();
        jumpRunable=new Runnable() {
            @Override
            public void run() {
                //跳转到Home
                Intent intent=new Intent();
                intent.setClass(WelcomeActivity.this,HomeActivity.class);
                startActivity(intent);
                WelcomeActivity.this.finish();
            }
        };
    }

    @Override
    public void InitView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_welcome);
    }

    @Override
    public void onRequestsData() {
        handler.postDelayed(jumpRunable,2000);
    }
}
