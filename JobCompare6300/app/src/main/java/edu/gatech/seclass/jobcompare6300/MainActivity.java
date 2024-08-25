package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnCurrentJob, btnJobOffer, btnComparisonSettings, btnCompareJobs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        btnCurrentJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CurrentJobActivity.class);
                startActivity(intent);
            }
        });
        btnJobOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, JobOfferActivity.class);
                startActivity(intent);
            }
        });
        btnComparisonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ComparisonSettingsActivity.class);
                startActivity(intent);
            }
        });
        btnCompareJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CompareListActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {

    }

    private void initViews(){
        btnCurrentJob = findViewById(R.id.CurrentJobID);
        btnJobOffer = findViewById(R.id.JobOffersID);
        btnComparisonSettings = findViewById(R.id.ComparisonSettingsID);
        btnCompareJobs = findViewById(R.id.CompareJobsID);

    }
}