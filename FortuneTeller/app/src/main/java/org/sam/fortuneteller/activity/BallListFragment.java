package org.sam.fortuneteller.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.sam.fortuneteller.model.BallResult;
import org.sam.fortuneteller.model.Results;

/**
 * Created by SAM on 2018/1/16.
 */
public class BallListFragment extends ListFragment {

    private Results results = new Results();

    private BallResultClickListener ballResultClickListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ArrayAdapter<BallResult> ballResultArrayAdapter = new ArrayAdapter<BallResult>(inflater.getContext(),
                android.R.layout.simple_list_item_1, results.getBalls());
        setListAdapter(ballResultArrayAdapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ballResultClickListener = (BallResultClickListener)getActivity();
    }

    public void setSavedPath(String savedPath) {
        results.setSavedPath(savedPath);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (null != ballResultClickListener) {
            ballResultClickListener.itemClick(results.get((int)id));
        }

    }
}
