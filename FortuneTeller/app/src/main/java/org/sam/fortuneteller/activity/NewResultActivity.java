package org.sam.fortuneteller.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.sam.fortuneteller.R;
import org.sam.fortuneteller.model.BallResult;
import org.sam.fortuneteller.model.Results;

/**
 * Created by Sam on 2017/12/2.
 */
public class NewResultActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);
    }
}