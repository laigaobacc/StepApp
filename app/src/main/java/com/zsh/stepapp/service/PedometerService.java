package com.zsh.stepapp.service;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import com.zsh.stepapp.beans.PedometerBean;


public class PedometerService extends Service
{

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
