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

public class JobOfferActivity extends AppCompatActivity {

    private EditText jTitle, jCompany, jLocation, jYsalary, jYbonus, jLeave, jStock, jHomeFund, jWellnessFund;
    private Button btnJobOfferBack, btnJobOfferSave, btnJobOfferAdd, btnJobOfferCompare;
    private MySQLiteOpenHelper mySQLiteOpenHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_offer);

        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);

        initViews();

        btnJobOfferBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JobOfferActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnJobOfferSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO : data base connection needed for job offer
                //SanityCheck
                ClearErrorMsg();

                if(mySQLiteOpenHelper.queryJobByTitleAndCompany(jTitle.getText().toString(), jCompany.getText().toString()).getTitle() != null){
                    Toast.makeText(JobOfferActivity.this, "Job Offer Already Exists", Toast.LENGTH_LONG).show();
                    return;
                }
                if (checkJobOfferValues()){
                    if(saveJobOffer() != -1){
                        Toast.makeText(JobOfferActivity.this, "Job Offer Saved", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        btnJobOfferAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO : Reset Edit Text
                ClearErrorMsg();
                Toast.makeText(JobOfferActivity.this, "Make Sure to Save before Adding Another", Toast.LENGTH_SHORT).show();
                setEmptyText();

            }
        });

        btnJobOfferCompare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO : connect this with current job and show in compare view
                Intent intent = new Intent(JobOfferActivity.this, CompareJobsActivity.class);
                Job currentJob = mySQLiteOpenHelper.queryCurrentJob();
                if(currentJob.getTitle() == null){
                    Toast.makeText(JobOfferActivity.this, "No Current Job Saved", Toast.LENGTH_SHORT).show();
                    return;
                }
                Bundle bundle = new Bundle();
                CommonUtils.convertFirstJobToBundle(bundle, currentJob);
                Job currentJobOffer = mySQLiteOpenHelper.queryJobByTitleAndCompany(jTitle.getText().toString(), jCompany.getText().toString());
                if(currentJobOffer.getTitle() == null){
                    Toast.makeText(JobOfferActivity.this, "This Job Offer Has Not Been Saved", Toast.LENGTH_SHORT).show();
                    return;
                }
                CommonUtils.convertSecondJobToBundle(bundle, currentJobOffer);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }



    private void initViews(){

        btnJobOfferAdd = findViewById(R.id.JobOfferaddID);
        btnJobOfferBack = findViewById(R.id.JobOfferbackID);
        btnJobOfferSave = findViewById(R.id.JobOffersaveID);
        btnJobOfferCompare = findViewById(R.id.JobOffercomparID);

        jTitle = findViewById(R.id.editJobOffertitle);
        jCompany = findViewById(R.id.editJobOfferComapny);
        jLocation = findViewById(R.id.editJobOfferLocation);
        jYsalary = findViewById(R.id.editJobOfferysalary);
        jYbonus = findViewById(R.id.editJobOfferybonus);
        jLeave = findViewById(R.id.editJobOfferleave);
        jStock = findViewById(R.id.editJobOfferstock);
        jHomeFund = findViewById(R.id.editJobOfferhomeFund);
        jWellnessFund = findViewById(R.id.editJobOfferwellnessFund);

    }

    public long saveJobOffer(){
        String title = jTitle.getText().toString();
        String company = jCompany.getText().toString();
        String location = jLocation.getText().toString();
        Integer yearlySalary = Integer.parseInt(jYsalary.getText().toString());
        Integer yearlyBonus = Integer.parseInt(jYbonus.getText().toString());
        Integer leaveTime = Integer.parseInt(jLeave.getText().toString());
        Integer stock = Integer.parseInt(jStock.getText().toString());
        Integer hbpFund = Integer.parseInt(jHomeFund.getText().toString());
        Integer wellnessFund = Integer.parseInt(jWellnessFund.getText().toString());
        Job job = new Job(title, company, location, yearlySalary, yearlyBonus, leaveTime, stock, hbpFund, wellnessFund, 0.0, "Job Offer");
        ComparisonSetting setting = mySQLiteOpenHelper.querySettings();
        CommonUtils.calculateAndSetJobScore(job, setting);
        return mySQLiteOpenHelper.insertData(job);
    }
    private boolean checkJobOfferValues(){
        String title = jTitle.getText().toString();
        String company = jCompany.getText().toString();
        String location = jLocation.getText().toString();
        Boolean check = true;


        if (title.isEmpty()) {
            jTitle.requestFocus();
            jTitle.setError("Please enter Title of the Job");
            check = false;
        }
        if (company.isEmpty()) {
            jCompany.requestFocus();
            jCompany.setError("Please enter Company Name");
            check = false;
        }
        if (location.isEmpty()) {
            jLocation.requestFocus();
            jLocation.setError("Please enter the Location");
            check = false;
        }
        if (jYsalary.getText().toString().isEmpty() ) {
            jYsalary.requestFocus();
            jYsalary.setError("Please enter Yearly Salary");
            check = false;
        }
        if (jYbonus.getText().toString().isEmpty() ) {
            jYbonus.requestFocus();
            jYbonus.setError("Please enter Yearly Bonus");
            check = false;
        }
        if (jLeave.getText().toString().isEmpty()) {
            jLeave.requestFocus();
            jLeave.setError("Please enter Leave time in days");
            check = false;
        }
        if (jStock.getText().toString().isEmpty()) {
            jStock.requestFocus();
            jStock.setError("Please enter the number of stock option shares offered");
            check = false;
        }
        if (jHomeFund.getText().toString().isEmpty()) {
            jHomeFund.requestFocus();
            jHomeFund.setError("Please enter the Home Buying Program Fund Value");
            check = false;
        }
        if (jWellnessFund.getText().toString().isEmpty()) {
            jWellnessFund.requestFocus();
            jWellnessFund.setError("Please enter the Wellness Fund Value");
            check = false;
        }
        else {

            Integer yearlySalary = Integer.parseInt(jYsalary.getText().toString());
            Integer homeBuyingProgramFund = Integer.parseInt(jHomeFund.getText().toString());
            Integer wellnessFund = Integer.parseInt(jWellnessFund.getText().toString());

            if (homeBuyingProgramFund > 0.15 * yearlySalary) {
                jHomeFund.requestFocus();
                jHomeFund.setError("Please Enter fund value less than or equal to 15% of your yearly income");
                check = false;
            }
            if (wellnessFund > 5000) {
                jWellnessFund.requestFocus();
                jWellnessFund.setError("Fund value over 5000 is not valid");
                check = false;
            }
        }
        return check;
    }

    private void setEmptyText(){
        jTitle.setText("");
        jCompany.setText("");
        jLocation.setText("");
        jYsalary.setText("");
        jYbonus.setText("");
        jLeave.setText("");
        jStock.setText("");
        jHomeFund.setText("");
        jWellnessFund.setText("");
    }

    private void ClearErrorMsg(){
        jTitle.setError(null);
        jCompany.setError(null);
        jLocation.setError(null);
        jYsalary.setError(null);
        jYbonus.setError(null);
        jLeave.setError(null);
        jStock.setError(null);
        jHomeFund.setError(null);
        jWellnessFund.setError(null);
    }
}