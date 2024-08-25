package edu.gatech.seclass.jobcompare6300;

import static android.app.PendingIntent.getActivity;
import static androidx.test.InstrumentationRegistry.getContext;
import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.Root;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;


import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.*;
import static edu.gatech.seclass.jobcompare6300.JobRecyclerViewAdapter.*;

import java.io.File;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {



    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("edu.gatech.seclass.jobcompare6300", appContext.getPackageName());
    }

    @Rule
    public ActivityScenarioRule<MainActivity> tActivityRule = new ActivityScenarioRule<>(
            MainActivity.class);

//    @Test
//    public void addition_isCorrect() {
//        assertEquals(4, 2 + 2);
//    }

    /*** TEST:0 wipe database*/
    @Test
    public void TEST_00_WipeDatabaseTest() {
        SQLiteDatabase.deleteDatabase(new File("/data/data/edu.gatech.seclass.jobcompare6300/databases/CS6300_SQLite.db"));
        assertEquals( false, new File("/data/data/edu.gatech.seclass.jobcompare6300/databases/CS6300_SQLite.db").exists());

    }

    /*** TEST:1 Simple app navigation checking*/
    @Test
    public void TEST_01_SimpleAppNavigationCheckingTest(){
        onView(withId(R.id.CurrentJobID)).perform(click());
        onView(withId(R.id.CurrentJobbackID)).perform(click());
        onView(withId(R.id.JobOffersID)).perform(click());
        onView(withId(R.id.JobOfferbackID)).perform(click());
        onView(withId(R.id.ComparisonSettingsID)).perform(click());
        onView(withId(R.id.ComparisonSettingsbackID)).perform(click());
        onView(withId(R.id.CompareJobsID)).perform(click());
        onView(withId(R.id.JobListBackID)).perform(click());
    }
    /*** TEST:2 Save current job information without filling in all the fields*/
    @Test
    public void TEST_02_SaveCurrentJobWithNullValuesTest() {
        onView(withId(R.id.CurrentJobID)).perform(click());
        onView(withId(R.id.editCurrentJobtitle)).perform(clearText(), replaceText("Doctor"));
        onView(withId(R.id.editCurrentJobComapny)).perform(clearText(), replaceText(""));
        onView(withId(R.id.editCurrentJobLocation)).perform(clearText(), replaceText(""));
        onView(withId(R.id.editCurrentJobysalary)).perform(clearText(), replaceText("45789"));
        onView(withId(R.id.editCurrentJobybonus)).perform(clearText(), replaceText(""));
        onView(withId(R.id.editCurrentJobleave)).perform(clearText(), replaceText(""));
        onView(withId(R.id.editCurrentJobstock)).perform(clearText(), replaceText(""));
        onView(withId(R.id.editCurrentJobhomeFund)).perform(clearText(), replaceText("34"));
        onView(withId(R.id.editCurrentJobwellnessFund)).perform(clearText(), replaceText("3"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.CurrentJobsaveID)).perform(click());
        onView(withId(R.id.editCurrentJobComapny)).check(matches(hasErrorText("Please enter Company Name")));
        onView(withId(R.id.editCurrentJobLocation)).check(matches(hasErrorText("Please enter the Location")));

        onView(withId(R.id.editCurrentJobybonus)).check(matches(hasErrorText("Please enter Yearly Bonus")));
        onView(withId(R.id.editCurrentJobleave)).check(matches(hasErrorText("Please enter Leave time in days")));
        onView(withId(R.id.editCurrentJobstock)).check(matches(hasErrorText("Please enter the number of stock option shares offered")));
    }
    /*** TEST:3 Save current job information after filling in all the fields*/
    @Test
    public void TEST_03_SaveCurrentJobTest() {
        onView(withId(R.id.CurrentJobID)).perform(click());
        onView(withId(R.id.editCurrentJobtitle)).perform(clearText(), replaceText("App Tester"));
        onView(withId(R.id.editCurrentJobComapny)).perform(clearText(), replaceText("Gatech"));
        onView(withId(R.id.editCurrentJobLocation)).perform(clearText(), replaceText("Atlanta"));
        onView(withId(R.id.editCurrentJobysalary)).perform(clearText(), replaceText("54000"));
        onView(withId(R.id.editCurrentJobybonus)).perform(clearText(), replaceText("800"));
        onView(withId(R.id.editCurrentJobleave)).perform(clearText(), replaceText("45"));
        onView(withId(R.id.editCurrentJobstock)).perform(clearText(), replaceText("11"));
        onView(withId(R.id.editCurrentJobhomeFund)).perform(clearText(), replaceText("8"));
        onView(withId(R.id.editCurrentJobwellnessFund)).perform(clearText(), replaceText("9"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.CurrentJobsaveID)).perform(click());
    }

    /*** TEST:4 Insert inappropriate value when adding current job (1)*/
    @Test
    public void TEST_04_InsertInappropriateValuesOnCurrentJobTest() {
        onView(withId(R.id.CurrentJobID)).perform(click());
        onView(withId(R.id.editCurrentJobtitle)).perform(clearText(), replaceText("Project Manager"));
        onView(withId(R.id.editCurrentJobComapny)).perform(clearText(), replaceText("HP"));
        onView(withId(R.id.editCurrentJobLocation)).perform(clearText(), replaceText("Baltimore"));
        onView(withId(R.id.editCurrentJobysalary)).perform(clearText(), replaceText("100000"));
        onView(withId(R.id.editCurrentJobybonus)).perform(clearText(), replaceText("45"));
        onView(withId(R.id.editCurrentJobleave)).perform(clearText(), replaceText("36"));
        onView(withId(R.id.editCurrentJobstock)).perform(clearText(), replaceText("45"));
        onView(withId(R.id.editCurrentJobhomeFund)).perform(clearText(), replaceText("20000"));
        onView(withId(R.id.editCurrentJobwellnessFund)).perform(clearText(), replaceText("56"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.CurrentJobsaveID)).perform(click());
        onView(withId(R.id.editCurrentJobhomeFund)).check(matches(hasErrorText("Please Enter fund value less than or equal to 15% of your yearly income")));

    }
    /*** TEST:5 Insert inappropriate value when adding current job (2)*/
    @Test
    public void TEST_05_InsertInappropriateValuesCurrentJobWellnessFundTest() {
        onView(withId(R.id.CurrentJobID)).perform(click());
        onView(withId(R.id.editCurrentJobtitle)).perform(clearText(), replaceText("Project Manager"));
        onView(withId(R.id.editCurrentJobComapny)).perform(clearText(), replaceText("HP"));
        onView(withId(R.id.editCurrentJobLocation)).perform(clearText(), replaceText("Baltimore"));
        onView(withId(R.id.editCurrentJobysalary)).perform(clearText(), replaceText("10000"));
        onView(withId(R.id.editCurrentJobybonus)).perform(clearText(), replaceText("45"));
        onView(withId(R.id.editCurrentJobleave)).perform(clearText(), replaceText("36"));
        onView(withId(R.id.editCurrentJobstock)).perform(clearText(), replaceText("45"));
        onView(withId(R.id.editCurrentJobhomeFund)).perform(clearText(), replaceText("2"));
        onView(withId(R.id.editCurrentJobwellnessFund)).perform(clearText(), replaceText("10000"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.CurrentJobsaveID)).perform(click());
        onView(withId(R.id.editCurrentJobwellnessFund )).check(matches(hasErrorText("Fund value over 5000 is not valid")));

    }
    /*** TEST:6 Edit current job information */
    @Test
    public void TEST_06_EditCurrentJobInformationTest() {
        onView(withId(R.id.CurrentJobID)).perform(click());
        onView(withId(R.id.editCurrentJobtitle)).perform(clearText(), replaceText("Dentist"));
        onView(withId(R.id.editCurrentJobComapny)).perform(clearText(), replaceText("Atlanta Hospital"));
        onView(withId(R.id.editCurrentJobLocation)).perform(clearText(), replaceText("Atlanta"));
        onView(withId(R.id.editCurrentJobysalary)).perform(clearText(), replaceText("84000"));
        onView(withId(R.id.editCurrentJobybonus)).perform(clearText(), replaceText("800"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.CurrentJobsaveID)).perform(click());
    }

    /***  TEST:6 Add multiple job offersR */
    @Test
    public void TEST_07_AddMultipleJobOffersTest() {
        onView(withId(R.id.JobOffersID)).perform(click());
        onView(withId(R.id.editJobOffertitle)).perform(clearText(), replaceText("App Tester_2"));
        onView(withId(R.id.editJobOfferComapny)).perform(clearText(), replaceText("Gatech_2"));
        onView(withId(R.id.editJobOfferLocation)).perform(clearText(), replaceText("Atlanta_2"));
        onView(withId(R.id.editJobOfferysalary)).perform(clearText(), replaceText("64000"));
        onView(withId(R.id.editJobOfferybonus)).perform(clearText(), replaceText("800"));
        onView(withId(R.id.editJobOfferleave)).perform(clearText(), replaceText("45"));
        onView(withId(R.id.editJobOfferstock)).perform(clearText(), replaceText("11"));
        onView(withId(R.id.editJobOfferhomeFund)).perform(clearText(), replaceText("8"));
        onView(withId(R.id.editJobOfferwellnessFund)).perform(clearText(), replaceText("9"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.JobOffersaveID)).perform(click());
        onView(withId(R.id.JobOfferaddID)).perform(click());
        onView(withId(R.id.editJobOffertitle)).perform(clearText(), replaceText("App Tester_3"));
        onView(withId(R.id.editJobOfferComapny)).perform(clearText(), replaceText("Gatech_3"));
        onView(withId(R.id.editJobOfferLocation)).perform(clearText(), replaceText("Atlanta_3"));
        onView(withId(R.id.editJobOfferysalary)).perform(clearText(), replaceText("44000"));
        onView(withId(R.id.editJobOfferybonus)).perform(clearText(), replaceText("100"));
        onView(withId(R.id.editJobOfferleave)).perform(clearText(), replaceText("25"));
        onView(withId(R.id.editJobOfferstock)).perform(clearText(), replaceText("11"));
        onView(withId(R.id.editJobOfferhomeFund)).perform(clearText(), replaceText("5"));
        onView(withId(R.id.editJobOfferwellnessFund)).perform(clearText(), replaceText("4"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.JobOffersaveID)).perform(click());
    }

    /*** Adjust comparison settings */
    @Test
    public void TEST_08_AdjustComparisonSettingsTest() {

        onView(withId(R.id.ComparisonSettingsID)).perform(click());
        onView(withId(R.id.seekBar1)).perform(setProgress(10));
        onView(withId(R.id.seekBar2)).perform(setProgress(2));
        onView(withId(R.id.seekBar3)).perform(setProgress(8));
        onView(withId(R.id.seekBar4)).perform(setProgress(9));
        onView(withId(R.id.seekBar5)).perform(setProgress(4));
        onView(withId(R.id.seekBar6)).perform(setProgress(7));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.ComparisonSettingssaveID)).perform(click());

    }

    /*** View adjusted comparison settings */
    @Test
    public void TEST_09_ViewAdjustedComparisonSettingsTest() {

        onView(withId(R.id.ComparisonSettingsID)).perform(click());
        onView(withId(R.id.seekBar1)).check(matches(withProgress(10)));
        onView(withId(R.id.seekBar2)).check(matches(withProgress(2)));
        onView(withId(R.id.seekBar3)).check(matches(withProgress(8)));
        onView(withId(R.id.seekBar4)).check(matches(withProgress(9)));
        onView(withId(R.id.seekBar5)).check(matches(withProgress(4)));
        onView(withId(R.id.seekBar6)).check(matches(withProgress(7)));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.ComparisonSettingssaveID)).perform(click());
    }

    /*** Compare current job with currently added job offer*/
    @Test
    public void TEST_10_CompareCurrentJobWithNewJobOfferTest(){
        onView(withId(R.id.CompareJobsID)).perform(click());
        onView(withId(R.id.JobListRecyclerViewID)).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.JobListRecyclerViewID)).perform(actionOnItemAtPosition(1, click()));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.JobListCompareID)).perform(click());
    }

    /*** Compare two jobs*/
    @Test
    public void TEST_11_CompareTwoJobsTest(){
        onView(withId(R.id.CompareJobsID)).perform(click());
        onView(withId(R.id.JobListRecyclerViewID)).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.JobListRecyclerViewID)).perform(actionOnItemAtPosition(1, click()));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.JobListCompareID)).perform(click());
        onView(withId(R.id.JobsComparisonComparebtnID)).perform(click());
        onView(withId(R.id.JobListRecyclerViewID)).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.JobListRecyclerViewID)).perform(actionOnItemAtPosition(1, click()));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.JobListCompareID)).perform(click());
    }
    /*** Compare less than or more than two jobs*/
    @Test
    public void TEST_12_CompareLessOrMoreThanTwoJobsTest(){
        onView(withId(R.id.CompareJobsID)).perform(click());
        onView(withId(R.id.JobListRecyclerViewID)).perform(actionOnItemAtPosition(0, click()));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.JobListCompareID)).perform(click());

        onView(withId(R.id.textViewJobsComparison)).check(doesNotExist());

        //onView(withText("Select Two Jobs, Now: 1")).inRoot((Matcher<Root>) new CompareListActivity()).check(matches(isDisplayed()));

        onView(withId(R.id.JobListBackID)).perform(click());
        onView(withId(R.id.CompareJobsID)).perform(click());
        onView(withId(R.id.JobListRecyclerViewID)).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.JobListRecyclerViewID)).perform(actionOnItemAtPosition(1, click()));
        onView(withId(R.id.JobListRecyclerViewID)).perform(actionOnItemAtPosition(2, click()));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.JobListCompareID)).perform(click());

        onView(withId(R.id.textViewJobsComparison)).check(doesNotExist());

        onView(withId(R.id.JobListBackID)).perform(click());
        onView(withId(R.id.CompareJobsID)).perform(click());
        onView(withId(R.id.JobListRecyclerViewID)).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.JobListRecyclerViewID)).perform(actionOnItemAtPosition(1, click()));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.JobListCompareID)).perform(click());

        onView(withId(R.id.textViewJobsComparison)).check(matches(not(doesNotExist())));

        //onView(withText("Select Two Jobs, Now: 2")).inRoot((Matcher<Root>) new CompareListActivity()).check(matches(isDisplayed()));
    }

    /*** Check the compare job list order */
    @Test
    public void TEST_13_CheckCompareJobListTest(){
        onView(withId(R.id.CompareJobsID)).perform(click());
        
    }
//*************REFERENCE https://stackoverflow.com/questions/23659367/espresso-set-seekbar
    public static ViewAction setProgress(final int progress) {
        return new ViewAction() {
            @Override
            public void perform(UiController uiController, View view) {
                SeekBar seekBar = (SeekBar) view;
                seekBar.setProgress(progress);
            }
            @Override
            public String getDescription() {
                return "Set a progress on a SeekBar";
            }
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(SeekBar.class);
            }
        };
    }
    public static Matcher<View> withProgress(final int expectedProgress) {
        return new BoundedMatcher<View, SeekBar>(SeekBar.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("expected: ");
                description.appendText(""+expectedProgress);
            }

            @Override
            public boolean matchesSafely(SeekBar seekBar) {
                return seekBar.getProgress() == expectedProgress;
            }
        };
    }
    //***************************************************************************************


}