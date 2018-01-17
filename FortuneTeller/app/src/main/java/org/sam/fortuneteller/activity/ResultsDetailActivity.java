package org.sam.fortuneteller.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import org.sam.fortuneteller.R;
import org.sam.fortuneteller.model.BallResult;

/**
 * Created by Sam on 2017/12/2.
 */
public class ResultsDetailActivity extends AppCompatActivity implements BallResultClickListener{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);
    }

    @Override
    public void itemClick(BallResult ballResult) {

    }
}
