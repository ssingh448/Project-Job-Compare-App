package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.jobcompare6300.bean.Job;
import edu.gatech.seclass.jobcompare6300.utils.CommonUtils;

public class CompareListActivity extends AppCompatActivity {

    private Button btnJobsListBack, btnJobsListCompare;
    private RecyclerView jobsRecyclerView;
    private JobRecyclerViewAdapter adapter;
    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private List<String> checkList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_list);


        initViews();
        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        adapter = new JobRecyclerViewAdapter();
        jobsRecyclerView = findViewById(R.id.JobListRecyclerViewID);
        jobsRecyclerView.setAdapter(adapter);
        jobsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Job> jobList = mySQLiteOpenHelper.queryAllJobs();
        checkList = new ArrayList<>();
        /*jobs.add(new Job("title1", "Company1", "location1", 10000, 100, 10, 2, 3, 2, 2.0, "Current Job"));
        jobs.add(new Job("title2", "Company2", "location2", 10000, 100, 10, 2, 3, 2, 2.0, "Job Offer"));
        jobs.add(new Job("title3", "Company3", "location3", 10000, 100, 10, 2, 3, 2, 2.0, "Job Offer"));
        jobs.add(new Job("title4", "Company4", "location4", 10000, 100, 10, 2, 3, 2, 2.0, "Job Offer"));
        jobs.add(new Job("title5", "Company5", "location5", 10000, 100, 10, 2, 3, 2, 2.0, "Job Offer"));
        jobs.add(new Job("title6", "Company6", "location6", 10000, 100, 10, 2, 3, 2, 2.0, "Job Offer"));
        jobs.add(new Job("title7", "Company7", "location7", 10000, 100, 10, 2, 3, 2, 2.0, "Job Offer"));
        jobs.add(new Job("title8", "Company8", "location8", 10000, 100, 10, 2, 3, 2, 2.0, "Job Offer"));*/

        adapter.setJobs(jobList);
        adapter.setOnItemClickListener(new JobRecyclerViewAdapter.OnItemClickListener(){

            @Override
            public void onClick(View view, int position) {
                if(checkList.contains(String.valueOf(position))){
                    checkList.remove(String.valueOf(position));
                }
                else{
                    checkList.add(String.valueOf(position));
                }
            }
        });


        btnJobsListBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CompareListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnJobsListCompare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkList.size() != 2){
                    Toast.makeText(CompareListActivity.this, "Select Two Jobs, Now: " + Integer.toString(checkList.size()), Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(CompareListActivity.this, CompareJobsActivity.class);
                    Bundle bundle = new Bundle();
                    Job firstJob = mySQLiteOpenHelper.queryJobByRank(checkList.get(0));
                    CommonUtils.convertFirstJobToBundle(bundle, firstJob);
                    Job secondJob = mySQLiteOpenHelper.queryJobByRank(checkList.get(1));
                    CommonUtils.convertSecondJobToBundle(bundle, secondJob);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CompareListActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void initViews(){

        btnJobsListBack = findViewById(R.id.JobListBackID);
        btnJobsListCompare = findViewById(R.id.JobListCompareID);

    }
}