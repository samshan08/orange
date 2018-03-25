package org.sam.fortuneteller.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.sam.fortuneteller.R;
import org.sam.fortuneteller.adapter.BallItemAdapter;
import org.sam.fortuneteller.data.BallResultsDataHelper;
import org.sam.fortuneteller.model.BallResult;
import org.sam.fortuneteller.model.Consts;
import org.sam.fortuneteller.model.Results;

/**
 * 进入启动占卜页面
 * @author SAM
 */
public class BallListActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ball_list);

        String resultsKey = getIntent().getExtras().getString(Consts.KEY_RESULTS_KEY);
        BallResultsDataHelper ballResultsDataHelper = new BallResultsDataHelper(getApplicationContext());
        Results results = ballResultsDataHelper.loadBallResults(resultsKey);

        ListView ballLists = getListView();
        BallItemAdapter ballListsAdapter = new BallItemAdapter(
                this,
                R.layout.activity_ball_item,
                results.getBalls());
        ballLists.setAdapter(ballListsAdapter);
        ballLists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onListItemClick(parent, view, position, id);
            }
        });
    }

    public ListView getListView() {
        return (ListView) findViewById(R.id.list_balls);
    }

    protected void onListItemClick(AdapterView<?> listView, View view, int position, long id) {
        Intent intent = new Intent(this, ResultsDetailActivity.class);
        BallResult selectedItem = (BallResult) listView.getItemAtPosition(position);
        intent.putExtra(Consts.KEY_RESULT_ITEM_KEY, selectedItem);
        startActivity(intent);
    }
}
