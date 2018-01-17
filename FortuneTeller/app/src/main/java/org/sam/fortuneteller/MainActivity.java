package org.sam.fortuneteller;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import org.sam.fortuneteller.activity.ResultsDetailActivity;
import org.sam.fortuneteller.activity.StartFortuneActivity;
import org.sam.fortuneteller.data.BallResultsDataHelper;
import org.sam.fortuneteller.model.Consts;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        BallResultsDataHelper dataHelper = new BallResultsDataHelper(getApplicationContext());
        SQLiteDatabase readableDatabase = dataHelper.getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery("SELECT COUNT(*) FROM " + Consts.TABLE_NAME, null);
        int count = cursor.getInt(0);
        View viewById = findViewById(R.id.cont);
        viewById.setEnabled(count > 0);
    }

}