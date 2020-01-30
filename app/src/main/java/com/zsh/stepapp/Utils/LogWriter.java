package com.zsh.stepapp.Utils;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogWriter {
    private static final String DEBUG_TAG = "Mystep";
    private static boolean isDebug = true;
    private static boolean isWriyeToLog = false;
    //将日志写入到文件
    public static void LogToFile(boolean isToFile,String tag, final String logText) {
        isWriyeToLog=isToFile;
        if (!isWriyeToLog) {
            return;
        }
        String needWriteMessage = tag + " : " + logText;
        String fileName = Environment.getExternalStorageDirectory().getPath()
                + "/LogFile.txt";
        File file = new File(fileName);

        try {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(needWriteMessage);
            bw.newLine();
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //输出控制台
    public static void d(boolean isToFile,String msg)
    {
        if (LogWriter.isDebug)
        {
            Log.d(DEBUG_TAG,msg);
        }
        if (isWriyeToLog)
        {
            LogToFile(isToFile,DEBUG_TAG,msg);
        }
    }
}
