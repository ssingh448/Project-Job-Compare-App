package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.gatech.seclass.jobcompare6300.bean.ComparisonSetting;
import edu.gatech.seclass.jobcompare6300.bean.Job;
import edu.gatech.seclass.jobcompare6300.utils.CommonUtils;

public class CurrentJobActivity extends AppCompatActivity {

    private EditText cjTitle, cjCompany, cjLocation, cjYsalary, cjYbonus, cjLeave, cjStock, cjHomeFund, cjWellnessFund;
    private Button btnCurrentJobBack, btnCurrentJobSave;
    private MySQLiteOpenHelper mySQLiteOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_job);

        initViews();
        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        displayCurrentJob();

        btnCurrentJobBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CurrentJobActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnCurrentJobSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO : data base connection needed for current job

                //clearTable();
                // Sanity Check
                ClearErrorMsg();
                if (checkJobValues()){
                    if(mySQLiteOpenHelper.queryCurrentJob().getTitle() == null){
                        if(saveCurrentJob() != -1){
                            Toast.makeText(CurrentJobActivity.this, "Current Job Saved", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        if(updateCurrentJob() != -1){
                            Toast.makeText(CurrentJobActivity.this, "Current Job Changed and Saved", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                //Add Job to the list
//                jobs.add(new Job("title1", "Company1", "location1", 10000, 100, 10, 2, 3, 2, 2.0));
            }
        });

    }


    public void clearTable(){
        mySQLiteOpenHelper.clearTable();
    }
    public long saveCurrentJob(){
        String title = cjTitle.getText().toString();
        String company = cjCompany.getText().toString();
        String location = cjLocation.getText().toString();
        Integer yearlySalary = Integer.parseInt(cjYsalary.getText().toString());
        Integer yearlyBonus = Integer.parseInt(cjYbonus.getText().toString());
        Integer leaveTime = Integer.parseInt(cjLeave.getText().toString());
        Integer stock = Integer.parseInt(cjStock.getText().toString());
        Integer hbpFund = Integer.parseInt(cjHomeFund.getText().toString());
        Integer wellnessFund = Integer.parseInt(cjWellnessFund.getText().toString());
        Job job = new Job(title, company, location, yearlySalary, yearlyBonus, leaveTime, stock, hbpFund, wellnessFund, 0.0, "Current Job");
        ComparisonSetting setting = mySQLiteOpenHelper.querySettings();
        CommonUtils.calculateAndSetJobScore(job, setting);
        return mySQLiteOpenHelper.insertData(job);
    }

    public int updateCurrentJob(){
        String title = cjTitle.getText().toString();
        String company = cjCompany.getText().toString();
        String location = cjLocation.getText().toString();
        Integer yearlySalary = Integer.parseInt(cjYsalary.getText().toString());
        Integer yearlyBonus = Integer.parseInt(cjYbonus.getText().toString());
        Integer leaveTime = Integer.parseInt(cjLeave.getText().toString());
        Integer stock = Integer.parseInt(cjStock.getText().toString());
        Integer hbpFund = Integer.parseInt(cjHomeFund.getText().toString());
        Integer wellnessFund = Integer.parseInt(cjWellnessFund.getText().toString());
        Job job = new Job(title, company, location, yearlySalary, yearlyBonus, leaveTime, stock, hbpFund, wellnessFund, 0.0, "Current Job");
        ComparisonSetting setting = mySQLiteOpenHelper.querySettings();
        CommonUtils.calculateAndSetJobScore(job, setting);
        return mySQLiteOpenHelper.updateCurrentJob(job);
    }

    public void displayCurrentJob(){
        Job job  = mySQLiteOpenHelper.queryCurrentJob();
        if(job.getTitle() != null){
            cjTitle.setText(job.getTitle());
            cjCompany.setText(job.getCompany());
            cjLocation.setText(job.getLocation());
            cjYsalary.setText(job.getYearlySalary().toString());
            cjYbonus.setText(job.getYearlyBonus().toString());
            cjLeave.setText(job.getLeaveTime().toString());
            cjStock.setText(job.getNumberofSharesOffered().toString());
            cjHomeFund.setText(job.getHomeBuyingProgramFund().toString());
            cjWellnessFund.setText(job.getWellnessFund().toString());
        }
    }

    private void initViews(){

        btnCurrentJobBack = findViewById(R.id.CurrentJobbackID);
        btnCurrentJobSave = findViewById(R.id.CurrentJobsaveID);

        cjTitle = findViewById(R.id.editCurrentJobtitle);
        cjCompany = findViewById(R.id.editCurrentJobComapny);
        cjLocation = findViewById(R.id.editCurrentJobLocation);
        cjYsalary = findViewById(R.id.editCurrentJobysalary);
        cjYbonus = findViewById(R.id.editCurrentJobybonus);
        cjLeave = findViewById(R.id.editCurrentJobleave);
        cjStock = findViewById(R.id.editCurrentJobstock);
        cjHomeFund = findViewById(R.id.editCurrentJobhomeFund);
        cjWellnessFund = findViewById(R.id.editCurrentJobwellnessFund);


    }

    private boolean checkJobValues(){
        String title = cjTitle.getText().toString();
        String company = cjCompany.getText().toString();
        String location = cjLocation.getText().toString();
        Boolean check = true;


        if (title.isEmpty()) {
            cjTitle.requestFocus();
            cjTitle.setError("Please enter Title of the Job");
            check = false;
        }
        if (company.isEmpty()) {
            cjCompany.requestFocus();
            cjCompany.setError("Please enter Company Name");
            check = false;
        }
        if (location.isEmpty()) {
            cjLocation.requestFocus();
            cjLocation.setError("Please enter the Location");
            check = false;
        }
        if (cjYsalary.getText().toString().isEmpty() ) {
            cjYsalary.requestFocus();
            cjYsalary.setError("Please enter Yearly Salary");
            check = false;
        }
        if (cjYbonus.getText().toString().isEmpty() ) {
            cjYbonus.requestFocus();
            cjYbonus.setError("Please enter Yearly Bonus");
            check = false;
        }
        if (cjLeave.getText().toString().isEmpty()) {
            cjLeave.requestFocus();
            cjLeave.setError("Please enter Leave time in days");
            check = false;
        }
        if (cjStock.getText().toString().isEmpty()) {
            cjStock.requestFocus();
            cjStock.setError("Please enter the number of stock option shares offered");
            check = false;
        }
        if (cjHomeFund.getText().toString().isEmpty()) {
            cjHomeFund.requestFocus();
            cjHomeFund.setError("Please enter the Home Buying Program Fund Value");
            check = false;
        }
        if (cjWellnessFund.getText().toString().isEmpty()) {
            cjWellnessFund.requestFocus();
            cjWellnessFund.setError("Please enter the Wellness Fund Value");
            check = false;
        }
        else {

            Integer yearlySalary = Integer.parseInt(cjYsalary.getText().toString());
            Integer homeBuyingProgramFund = Integer.parseInt(cjHomeFund.getText().toString());
            Integer wellnessFund = Integer.parseInt(cjWellnessFund.getText().toString());

            if (homeBuyingProgramFund > 0.15 * yearlySalary) {
                cjHomeFund.requestFocus();
                cjHomeFund.setError("Please Enter fund value less than or equal to 15% of your yearly income");
                check = false;
            }
            if (wellnessFund > 5000) {
                cjWellnessFund.requestFocus();
                cjWellnessFund.setError("Fund value over 5000 is not valid");
                check = false;
            }
        }
        return check;
    }

    private void ClearErrorMsg(){
        cjTitle.setError(null);
        cjCompany.setError(null);
        cjLocation.setError(null);
        cjYsalary.setError(null);
        cjYbonus.setError(null);
        cjLeave.setError(null);
        cjStock.setError(null);
        cjHomeFund.setError(null);
        cjWellnessFund.setError(null);
    }
}