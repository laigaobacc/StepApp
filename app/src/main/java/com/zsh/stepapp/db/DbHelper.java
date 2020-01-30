package com.zsh.stepapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.zsh.stepapp.beans.PedometerBean;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    public static final String TABLE_NAME = "pedometer";
    public static final String[] COLUMNS = {
            "id",
            "stepCount",//所走步数
            "calorie",//消耗卡路里
            "distance",//所走距离
            "pace",//步/每分钟
            "speed",//速度
            "startTime",//开始记录时间
            "lastStepTime",//最后一步的时间
            "day"//当天的时间
    };

    public DbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //execSQL用于执行SQL语句
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (id integer  PRIMARY KEY AUTOINCREMENT DEFAULT NULL," +
                "stepCount integer," +
                "calorie Double," +
                "distance Double DEFAULT NULL," +
                "pace INTEGER," +
                "speed Double," +
                "startTime Timestamp DEFAULT NULL," +
                "lastStepTime Timestamp  DEFAULT NULL," +
                "createTime Timestamp   DEFAULT NULL)");
    }

    /**
     * 将数据写入数据库
     *
     * @param bean
     */
    public void writeToDatabase(PedometerBean bean) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("stepCount", bean.getStepCount());
        values.put("calorie", bean.getCalorie());
        values.put("distance", bean.getDistance());
        values.put("pace", bean.getPace());
        values.put("speed", bean.getSpeed());
        values.put("startTime", bean.getStartTime());
        values.put("lastStepTime", bean.getLastStepTime());
        values.put("createTime", bean.getCreateTime());
        db.insert(DbHelper.TABLE_NAME, null, values);
        db.close();
    }

    /**
     * 通过日期查找数据
     */
    public PedometerBean getPedometerByDay(long day) {
        Cursor cursor = null;
        PedometerBean pedometerBean = null;
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery("select * from " + DbHelper.TABLE_NAME + "" +
                " where day=" + String.valueOf(day), null);
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToNext()) {
                pedometerBean = new PedometerBean();
                int id = cursor.getInt(cursor.getColumnIndex(DbHelper.COLUMNS[0]));
                int stepCount = cursor.getInt(cursor.getColumnIndex(DbHelper.COLUMNS[1]));
                double calorie = cursor.getDouble(cursor.getColumnIndex(DbHelper.COLUMNS[2]));
                double distance = cursor.getDouble(cursor.getColumnIndex(DbHelper.COLUMNS[3]));
                int pace = cursor.getInt(cursor.getColumnIndex(DbHelper.COLUMNS[4]));
                double speed = cursor.getDouble(cursor.getColumnIndex(DbHelper.COLUMNS[5]));
                long startTime = cursor.getLong(cursor.getColumnIndex(DbHelper.COLUMNS[6]));
                long lastStepTime = cursor.getLong(cursor.getColumnIndex(DbHelper.COLUMNS[7]));
                long cTime = cursor.getLong(cursor.getColumnIndex(DbHelper.COLUMNS[8]));
                pedometerBean.setId(id);
                pedometerBean.setStepCount(stepCount);
                pedometerBean.setCalorie(calorie);
                pedometerBean.setDistance(distance);
                pedometerBean.setPace(pace);
                pedometerBean.setSpeed(speed);
                pedometerBean.setStartTime(startTime);
                pedometerBean.setLastStepTime(lastStepTime);
                pedometerBean.setCreateTime(cTime);
            }
        }
        cursor.close();
        db.close();
        return pedometerBean;
    }

    /**
     * 获取到全部数据
     *
     * @return
     */
    public ArrayList<PedometerBean> getFromDatabase(int offVal) {
        int pageSize = 20;
        Cursor cursor = null;
        SQLiteDatabase db = this.getWritableDatabase();
        cursor = db.query(TABLE_NAME, null, null, null, null, null,
                "day desc limit " + String.valueOf(pageSize) + " offset " + String.valueOf(offVal), null);
        ArrayList<PedometerBean> dataList=new ArrayList<PedometerBean>();
        if (cursor!=null&&cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                PedometerBean pedometerBean = new PedometerBean();
                int id = cursor.getInt(cursor.getColumnIndex(DbHelper.COLUMNS[0]));
                int stepCount = cursor.getInt(cursor.getColumnIndex(DbHelper.COLUMNS[1]));
                double calorie = cursor.getDouble(cursor.getColumnIndex(DbHelper.COLUMNS[2]));
                double distance = cursor.getDouble(cursor.getColumnIndex(DbHelper.COLUMNS[3]));
                int pace = cursor.getInt(cursor.getColumnIndex(DbHelper.COLUMNS[4]));
                double speed = cursor.getDouble(cursor.getColumnIndex(DbHelper.COLUMNS[5]));
                long startTime = cursor.getLong(cursor.getColumnIndex(DbHelper.COLUMNS[6]));
                long lastStepTime = cursor.getLong(cursor.getColumnIndex(DbHelper.COLUMNS[7]));
                long cTime = cursor.getLong(cursor.getColumnIndex(DbHelper.COLUMNS[8]));
                pedometerBean.setId(id);
                pedometerBean.setStepCount(stepCount);
                pedometerBean.setCalorie(calorie);
                pedometerBean.setDistance(distance);
                pedometerBean.setPace(pace);
                pedometerBean.setSpeed(speed);
                pedometerBean.setStartTime(startTime);
                pedometerBean.setLastStepTime(lastStepTime);
                pedometerBean.setCreateTime(cTime);
                dataList.add(pedometerBean);
            }
        }
        cursor.close();
        db.close();
        return dataList;
    }

    /**
     * 根据日期修改数据
     */
    public void updateToDatabase(ContentValues values,long day){
            SQLiteDatabase db=this.getWritableDatabase();
            db.update(TABLE_NAME,values,"day=?",new String[]{String.valueOf(day)});
            db.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
