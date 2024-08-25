package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import edu.gatech.seclass.jobcompare6300.bean.ComparisonSetting;
import edu.gatech.seclass.jobcompare6300.bean.Job;
import edu.gatech.seclass.jobcompare6300.utils.CommonUtils;

public class ComparisonSettingsActivity extends AppCompatActivity {

    private SeekBar s1, s2, s3, s4, s5, s6;
    private Button btnComparisonSettingsBack, btnComparisonSettingsSave;
    private TextView tAYS, tAYB, tLT, tCSO, tHBP, tWF;
    private MySQLiteOpenHelper mySQLiteOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparison_settings);

        initViews();
        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);

        displaySettings();

        s1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tAYS.setText(Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.e("------------------------------", "Start sliding!");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.e("------------------------------", "Stop sliding!");
            }
        });

        s2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tAYB.setText(Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.e("------------------------------", "Start sliding!");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.e("------------------------------", "Stop sliding!");
            }
        });

        s3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tLT.setText(Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.e("------------------------------", "Start sliding!");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.e("------------------------------", "Stop sliding!");
            }
        });

        s4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tCSO.setText(Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.e("------------------------------", "Start sliding!");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.e("------------------------------", "Stop sliding!");
            }
        });

        s5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tHBP.setText(Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.e("------------------------------", "Start sliding!");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.e("------------------------------", "Stop sliding!");
            }
        });

        s6.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tWF.setText(Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.e("------------------------------", "Start sliding!");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.e("------------------------------", "Stop sliding!");
            }
        });

        btnComparisonSettingsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ComparisonSettingsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnComparisonSettingsSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO : Save Comparison Settings -> first create the class
                if(saveCurrentSettings() != -1){
                    Toast.makeText(ComparisonSettingsActivity.this, "Weights Assigned", Toast.LENGTH_SHORT).show();
                    updateJobScore();
                }
                else{
                    Toast.makeText(ComparisonSettingsActivity.this, "Invalid Weights", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initViews(){
        btnComparisonSettingsBack = findViewById(R.id.ComparisonSettingsbackID);
        btnComparisonSettingsSave = findViewById(R.id.ComparisonSettingssaveID);

        s1 = findViewById(R.id.seekBar1);
        s2 = findViewById(R.id.seekBar2);
        s3 = findViewById(R.id.seekBar3);
        s4 = findViewById(R.id.seekBar4);
        s5 = findViewById(R.id.seekBar5);
        s6 = findViewById(R.id.seekBar6);

        tAYS = findViewById(R.id.valueAYS);
        tAYB = findViewById(R.id.valueAYB);
        tLT = findViewById(R.id.valueLT);
        tCSO = findViewById(R.id.valueCSO);
        tHBP = findViewById(R.id.valueHBP);
        tWF = findViewById(R.id.valueWF);

    }

    private void displaySettings(){
        ComparisonSetting setting = mySQLiteOpenHelper.querySettings();
        if(setting == null){
            setting = new ComparisonSetting(1, 1, 1, 1, 1, 1);
        }
        s1.setProgress(setting.getWeightOfAYS());
        s2.setProgress(setting.getWeightOfAYB());
        s3.setProgress(setting.getWeightOfLT());
        s4.setProgress(setting.getWeightOfCSO());
        s5.setProgress(setting.getWeightOfHBP());
        s6.setProgress(setting.getWeightOfWF());

        tAYS.setText(setting.getWeightOfAYS().toString());
        tAYB.setText(setting.getWeightOfAYB().toString());
        tLT.setText(setting.getWeightOfLT().toString());
        tCSO.setText(setting.getWeightOfCSO().toString());
        tHBP.setText(setting.getWeightOfHBP().toString());
        tWF.setText(setting.getWeightOfWF().toString());
    }

    private long saveCurrentSettings(){
        Integer weightOfAYS = s1.getProgress();
        Integer weightOfAYB = s2.getProgress();
        Integer weightOfLT = s3.getProgress();
        Integer weightOfCSO = s4.getProgress();
        Integer weightOfHBP = s5.getProgress();
        Integer weightOfWF = s6.getProgress();
        ComparisonSetting setting = new ComparisonSetting(weightOfAYS, weightOfAYB, weightOfLT, weightOfCSO, weightOfHBP, weightOfWF);
        if(weightOfAYB == 0 && weightOfAYS == 0 && weightOfLT == 0 && weightOfCSO == 0 && weightOfHBP == 0 && weightOfWF == 0){
            return -1;
        }
        return (mySQLiteOpenHelper.updateSettings(setting));
    }

    private void updateJobScore(){
        List<Job> jobList = mySQLiteOpenHelper.queryAllJobs();
        ComparisonSetting setting = mySQLiteOpenHelper.querySettings();
        for(Job job: jobList){
            CommonUtils.calculateAndSetJobScore(job, setting);
            mySQLiteOpenHelper.updateJobScore(job);
        }
    }
}