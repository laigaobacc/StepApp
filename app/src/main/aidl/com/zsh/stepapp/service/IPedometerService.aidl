// IPedometerService.aidl
package com.zsh.stepapp.service;

// Declare any non-default types here with import statements

interface IPedometerService {


       void startCount();

       void stopCount();

       void resetCount();

       void getStepsCount();
       double getCalorie();
       double getDistance();
       void saveData();
       void setSensitivity(double sensitivity);
       double getSensitivity();
       void setInterval(int interval);
       int getInterval();
       long getStartTimeStamo();
       int getServiceStatus();


}
