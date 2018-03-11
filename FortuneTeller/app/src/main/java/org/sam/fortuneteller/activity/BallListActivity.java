package org.sam.fortuneteller.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.sam.fortuneteller.R;
import org.sam.fortuneteller.data.BallResultsDataHelper;
import org.sam.fortuneteller.model.BallResult;
import org.sam.fortuneteller.model.Consts;
import org.sam.fortuneteller.model.Results;

/**
 * 进入启动占卜页面
 */
public class BallListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ball_list);

        String resultsKey = (String) getIntent().getExtras().get(Consts.KEY_RESULTS_KEY);
        BallResultsDataHelper ballResultsDataHelper = new BallResultsDataHelper(getApplicationContext());
        Results results = ballResultsDataHelper.loadBallResults(resultsKey);

        ListView ballLists = getListView();
        ArrayAdapter<BallResult> ballListsAdapter = new ArrayAdapter<BallResult>(
                this,
                android.R.layout.simple_list_item_1,
                results.getBalls());
        ballLists.setAdapter(ballListsAdapter);
    }

    @Override
    protected void onListItemClick(ListView listView, View v, int position, long id) {
        Intent intent = new Intent(this, ResultsDetailActivity.class);
        BallResult selectedItem = (BallResult) listView.getSelectedItem();
        intent.putExtra(Consts.KEY_RESULTS_KEY, selectedItem.getParent().getCreateDate());
        intent.putExtra(Consts.KEY_RESULT_ITEM_KEY, selectedItem.getId());
        intent.putExtra(Consts.KEY_RESULT_ITEM_CONTENT, selectedItem.getContent());
        startActivity(intent);
    }
}
