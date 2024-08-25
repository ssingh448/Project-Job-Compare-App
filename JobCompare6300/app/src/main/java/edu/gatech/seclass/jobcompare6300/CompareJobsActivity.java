package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CompareJobsActivity extends AppCompatActivity {

    private Button btnCompareJobsBack, btnCompareJobsCompareAnother;
    private TextView txtJobType_1, txtTitle_1, txtCompany_1, txtLocation_1, txtSalary_1, txtBonus_1, txtLeaveTime_1, txtStock_1, txtHBPFund_1, txtWellnessFund_1;
    private TextView txtJobType_2, txtTitle_2, txtCompany_2, txtLocation_2, txtSalary_2, txtBonus_2, txtLeaveTime_2, txtStock_2, txtHBPFund_2, txtWellnessFund_2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_jobs);

        initViews();

        Bundle bundle = getIntent().getExtras();
        displayJobComparison(bundle);


        btnCompareJobsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CompareJobsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnCompareJobsCompareAnother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CompareJobsActivity.this, CompareListActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CompareJobsActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void initViews(){
        btnCompareJobsBack = findViewById(R.id.JobsComparisonbackID);
        btnCompareJobsCompareAnother = findViewById(R.id.JobsComparisonComparebtnID);

        txtJobType_1 = findViewById(R.id.jobTypeInfoID_1);
        txtTitle_1 = findViewById(R.id.titleInfoID_1);
        txtCompany_1 = findViewById(R.id.companyInfoID_1);
        txtLocation_1 = findViewById(R.id.locationInfoID_1);
        txtSalary_1 = findViewById(R.id.salaryInfoID_1);
        txtBonus_1 = findViewById(R.id.bonusInfoID_1);
        txtLeaveTime_1 = findViewById(R.id.leaveInfoID_1);
        txtStock_1 = findViewById(R.id.stockInfoID_1);
        txtHBPFund_1 = findViewById(R.id.HBPFundInfoID_1);
        txtWellnessFund_1 = findViewById(R.id.WellnessFundInfoID_1);

        txtJobType_2 = findViewById(R.id.jobTypeInfoID_2);
        txtTitle_2 = findViewById(R.id.titleInfoID_2);
        txtCompany_2 = findViewById(R.id.companyInfoID_2);
        txtLocation_2 = findViewById(R.id.locationInfoID_2);
        txtSalary_2 = findViewById(R.id.salaryInfoID_2);
        txtBonus_2 = findViewById(R.id.bonusInfoID_2);
        txtLeaveTime_2 = findViewById(R.id.leaveInfoID_2);
        txtStock_2 = findViewById(R.id.stockInfoID_2);
        txtHBPFund_2 = findViewById(R.id.HBPFundInfoID_2);
        txtWellnessFund_2 = findViewById(R.id.WellnessFundInfoID_2);

    }

    private void displayJobComparison(Bundle bundle){
        txtJobType_1.setText(bundle.getString("jobType_1"));
        txtTitle_1.setText(bundle.getString("title_1"));
        txtCompany_1.setText(bundle.getString("company_1"));
        txtLocation_1.setText(bundle.getString("location_1"));
        txtSalary_1.setText("$ " + bundle.getString("ySalary_1"));
        txtBonus_1.setText("$ " + bundle.getString("yBonus_1"));
        txtLeaveTime_1.setText(bundle.getString("leaveTime_1") + " Days");
        txtStock_1.setText(bundle.getString("stock_1") + " Shares");
        txtHBPFund_1.setText("$ " + bundle.getString("hbpFund_1"));
        txtWellnessFund_1.setText("$ " + bundle.getString("wellnessFund_1"));

        txtJobType_2.setText(bundle.getString("jobType_2"));
        txtTitle_2.setText(bundle.getString("title_2"));
        txtCompany_2.setText(bundle.getString("company_2"));
        txtLocation_2.setText(bundle.getString("location_2"));
        txtSalary_2.setText("$ " + bundle.getString("ySalary_2"));
        txtBonus_2.setText("$ " + bundle.getString("yBonus_2"));
        txtLeaveTime_2.setText(bundle.getString("leaveTime_2") + " Days");
        txtStock_2.setText(bundle.getString("stock_2") + " Shares");
        txtHBPFund_2.setText("$ " + bundle.getString("hbpFund_2"));
        txtWellnessFund_2.setText("$ " + bundle.getString("wellnessFund_2"));
    }
}