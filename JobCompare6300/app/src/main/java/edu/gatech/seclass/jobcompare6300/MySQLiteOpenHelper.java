package edu.gatech.seclass.jobcompare6300;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.CheckResult;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.jobcompare6300.bean.ComparisonSetting;
import edu.gatech.seclass.jobcompare6300.bean.Job;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "CS6300_SQLite.db";
    private static final String TABLE_NAME_JOB = "jobOffers";
    private static final String TABLE_NAME_SETTING = "comparisonSettings";
    private static final String TABLE_CLEAR_SQL = "DELETE FROM " + TABLE_NAME_JOB;
    private static final String CREATE_TABLE_JOB_SQL = "CREATE TABLE " + TABLE_NAME_JOB + " (id integer primary key autoincrement, " +
            "title varchar(255), company varchar(255), location varchar(255), yearly_salary integer, yearly_bonus integer, " +
            "leave_time integer, num_of_stock integer, hbp_fund integer, wellness_fund integer, job_score double, job_type varchar(255))";

    private static final String INITIALIZE_TABLE_SETTING_SQL = "INSERT INTO " + TABLE_NAME_SETTING + " (weight_of_AYS, weight_of_AYB, " +
            " weight_of_LT, weight_of_CSO, weight_of_HBP, weight_of_WF) VALUES(1, 1, 1, 1, 1, 1)";
    private static final String CREATE_TABLE_SETTING_SQL = "CREATE TABLE " + TABLE_NAME_SETTING + " (id integer primary key autoincrement, " +
            "weight_of_AYS integer DEFAULT 1, weight_of_AYB integer DEFAULT 1, weight_of_LT integer DEFAULT 1, weight_of_CSO integer DEFAULT 1, " +
            "weight_of_HBP integer DEFAULT 1, weight_of_WF integer DEFAULT 1)";
    public MySQLiteOpenHelper(Context context){
        super(context, DB_NAME, null, 1);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_JOB_SQL);
        db.execSQL(CREATE_TABLE_SETTING_SQL);
        db.execSQL(INITIALIZE_TABLE_SETTING_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void clearTable(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(TABLE_CLEAR_SQL);
    }
    public long insertData(Job job){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", job.getTitle());
        values.put("company", job.getCompany());
        values.put("location", job.getLocation());
        values.put("yearly_salary", job.getYearlySalary());
        values.put("yearly_bonus", job.getYearlyBonus());
        values.put("leave_time", job.getLeaveTime());
        values.put("num_of_stock", job.getNumberofSharesOffered());
        values.put("hbp_fund", job.getHomeBuyingProgramFund());
        values.put("wellness_fund", job.getWellnessFund());
        values.put("job_score", job.getJobScore());
        values.put("job_type", job.getJobType());
        return db.insert(TABLE_NAME_JOB, null, values);
    }

    public int updateCurrentJob(Job job){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", job.getTitle());
        values.put("company", job.getCompany());
        values.put("location", job.getLocation());
        values.put("yearly_salary", job.getYearlySalary());
        values.put("yearly_bonus", job.getYearlyBonus());
        values.put("leave_time", job.getLeaveTime());
        values.put("num_of_stock", job.getNumberofSharesOffered());
        values.put("hbp_fund", job.getHomeBuyingProgramFund());
        values.put("wellness_fund", job.getWellnessFund());
        values.put("job_score", job.getJobScore());
        return(db.update(TABLE_NAME_JOB, values, "job_type like ?", new String[]{"Current Job"}));
    }

    public int updateJobScore(Job job){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("job_score", job.getJobScore());
        return(db.update(TABLE_NAME_JOB, values, "id like ?", new String[]{job.getId().toString()}));
    }

    public Job queryCurrentJob(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME_JOB, null, "job_type like ?", new String[]{"Current Job"}, null, null, null);
        while(cursor.moveToNext()){
            String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
            String company = cursor.getString(cursor.getColumnIndexOrThrow("company"));
            String location = cursor.getString(cursor.getColumnIndexOrThrow("location"));
            Integer yearlySalary = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("yearly_salary")));
            Integer yearlyBonus = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("yearly_bonus")));
            Integer leaveTime = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("leave_time")));
            Integer stock = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("num_of_stock")));
            Integer hbpFund = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("hbp_fund")));
            Integer wellnessFund = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("wellness_fund")));
            Job job = new Job(title, company, location, yearlySalary, yearlyBonus, leaveTime, stock, hbpFund, wellnessFund, 0.0, "Current Job");
            return job;
        }
        return new Job();
    }

    public Job queryJobByTitleAndCompany(String title, String company){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME_JOB, null, "title like ? AND company like ?", new String[]{title, company}, null, null, null);
        return convertCursorToJob(cursor);
    }

    public Job queryJobByRank(String row){
        SQLiteDatabase db = getWritableDatabase();
        String QUERY_JOB_BY_RANK_SQL = "SELECT * FROM " + TABLE_NAME_JOB + " ORDER BY job_score DESC LIMIT " + row + ", 1";
        Cursor cursor = db.rawQuery(QUERY_JOB_BY_RANK_SQL, null);
        return convertCursorToJob(cursor);
    }

    public Job convertCursorToJob(Cursor cursor){
        while(cursor.moveToNext()){
            String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
            String company = cursor.getString(cursor.getColumnIndexOrThrow("company"));
            String jobType = cursor.getString(cursor.getColumnIndexOrThrow("job_type"));
            String location = cursor.getString(cursor.getColumnIndexOrThrow("location"));
            Integer yearlySalary = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("yearly_salary")));
            Integer yearlyBonus = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("yearly_bonus")));
            Integer leaveTime = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("leave_time")));
            Integer stock = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("num_of_stock")));
            Integer hbpFund = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("hbp_fund")));
            Integer wellnessFund = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("wellness_fund")));
            Double jobScore = Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow("job_score")));
            Job job = new Job(title, company, location, yearlySalary, yearlyBonus, leaveTime, stock, hbpFund, wellnessFund, jobScore, jobType);
            return job;
        }
        return new Job();
    }

    public List<Job> queryAllJobs(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME_JOB, null, null, null, null, null, "job_score DESC");
        List<Job> jobList = new ArrayList<>();
        while(cursor.moveToNext()){
            Integer id = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("id")));
            String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
            String company = cursor.getString(cursor.getColumnIndexOrThrow("company"));
            String jobType = cursor.getString(cursor.getColumnIndexOrThrow("job_type"));
            String location = cursor.getString(cursor.getColumnIndexOrThrow("location"));
            Integer yearlySalary = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("yearly_salary")));
            Integer yearlyBonus = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("yearly_bonus")));
            Integer leaveTime = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("leave_time")));
            Integer stock = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("num_of_stock")));
            Integer hbpFund = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("hbp_fund")));
            Integer wellnessFund = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("wellness_fund")));
            Double jobScore = Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow("job_score")));
            Job job = new Job(title, company, location, yearlySalary, yearlyBonus, leaveTime, stock, hbpFund, wellnessFund, jobScore, jobType);
            job.setId(id);
            jobList.add(job);
        }
        return jobList;
    }

    public ComparisonSetting querySettings(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME_SETTING, null, null, null, null, null, null);
        while(cursor.moveToNext()){
            Integer weightOfAYS = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("weight_of_AYS")));
            Integer weightOfAYB = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("weight_of_AYB")));
            Integer weightOfLT = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("weight_of_LT")));
            Integer weightOfCSO = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("weight_of_CSO")));
            Integer weightOfHBP = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("weight_of_HBP")));
            Integer weightOfWF = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("weight_of_WF")));
            return new ComparisonSetting(weightOfAYS, weightOfAYB, weightOfLT, weightOfCSO, weightOfHBP, weightOfWF);
        }
        return null;
    }


    public int updateSettings(ComparisonSetting setting){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("weight_of_AYS", setting.getWeightOfAYS());
        values.put("weight_of_AYB", setting.getWeightOfAYB());
        values.put("weight_of_LT", setting.getWeightOfLT());
        values.put("weight_of_CSO", setting.getWeightOfCSO());
        values.put("weight_of_HBP", setting.getWeightOfHBP());
        values.put("weight_of_WF", setting.getWeightOfWF());
        return(db.update(TABLE_NAME_SETTING, values, null,null));
    }

}
