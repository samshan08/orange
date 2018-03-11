package org.sam.fortuneteller;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import org.sam.fortuneteller.activity.BallListActivity;
import org.sam.fortuneteller.data.BallResultsDataHelper;
import org.sam.fortuneteller.model.Consts;
import org.sam.fortuneteller.model.Results;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onCreateFortune(View view) {
        //create the data record
        BallResultsDataHelper dataHelper = new BallResultsDataHelper(view.getContext());
        Results results = new Results();
        dataHelper.insertBallResults(results);
        Intent intent = new Intent(this, BallListActivity.class);
        intent.putExtra(Consts.KEY_RESULTS_KEY, results.getCreateDate());
        startActivity(intent);
    }


}