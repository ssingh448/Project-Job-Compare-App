package edu.gatech.seclass.jobcompare6300.utils;

import android.os.Bundle;

import edu.gatech.seclass.jobcompare6300.bean.ComparisonSetting;
import edu.gatech.seclass.jobcompare6300.bean.Job;

public class CommonUtils {
    public static void calculateAndSetJobScore(Job job, ComparisonSetting setting){
        int wAYS = setting.getWeightOfAYS();
        int wAYB = setting.getWeightOfAYB();
        int wLT = setting.getWeightOfLT();
        int wCSO = setting.getWeightOfCSO();
        int wHBP = setting.getWeightOfHBP();
        int wWF = setting.getWeightOfWF();

        int salary = job.getYearlySalary();
        int bonus = job.getYearlyBonus();
        int leaveTime = job.getLeaveTime();
        int stock = job.getNumberofSharesOffered();
        int hbpFund = job.getHomeBuyingProgramFund();
        int wellnessFund = job.getWellnessFund();

        int weightSum = wAYS + wAYB + wLT + wCSO + wHBP + wWF;
        double jobScore = (wAYS * salary + wAYB * bonus + wLT * leaveTime * salary / 260 + wCSO * stock / 2 + wHBP * hbpFund + wWF * wellnessFund) / weightSum;
        job.setJobScore(jobScore);
    }

    public static Bundle convertFirstJobToBundle(Bundle bundle, Job job){
        bundle.putString("jobType_1", job.getJobType());
        bundle.putString("title_1", job.getTitle());
        bundle.putString("company_1", job.getCompany());
        bundle.putString("location_1", job.getLocation());
        bundle.putString("ySalary_1", job.getYearlySalary().toString());
        bundle.putString("yBonus_1", job.getYearlyBonus().toString());
        bundle.putString("leaveTime_1", job.getLeaveTime().toString());
        bundle.putString("stock_1", job.getNumberofSharesOffered().toString());
        bundle.putString("hbpFund_1", job.getHomeBuyingProgramFund().toString());
        bundle.putString("wellnessFund_1", job.getWellnessFund().toString());
        return bundle;
    }
    public static Bundle convertSecondJobToBundle(Bundle bundle, Job job){
        bundle.putString("jobType_2", job.getJobType());
        bundle.putString("title_2", job.getTitle());
        bundle.putString("company_2", job.getCompany());
        bundle.putString("location_2", job.getLocation());
        bundle.putString("ySalary_2", job.getYearlySalary().toString());
        bundle.putString("yBonus_2", job.getYearlyBonus().toString());
        bundle.putString("leaveTime_2", job.getLeaveTime().toString());
        bundle.putString("stock_2", job.getNumberofSharesOffered().toString());
        bundle.putString("hbpFund_2", job.getHomeBuyingProgramFund().toString());
        bundle.putString("wellnessFund_2", job.getWellnessFund().toString());
        return bundle;
    }

}
