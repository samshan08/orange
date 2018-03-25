package org.sam.fortuneteller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import org.sam.fortuneteller.R;
import org.sam.fortuneteller.model.BallResult;

import java.util.List;

/**
 *
 * @author SAM
 * @date 2018/3/25
 */
public class BallItemAdapter extends ArrayAdapter<BallResult> {


    private int resourceId;

    /**
     * @param context
     * @param resourceId
     * @param ballResults
     */
    public BallItemAdapter(Context context, int resourceId, List<BallResult> ballResults) {
        super(context, resourceId, ballResults);
        this.resourceId = resourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BallResult item = getItem(position);
        View contentView = LayoutInflater.from(getContext()).inflate(resourceId, null);
        TextView ballName = (TextView) contentView.findViewById(R.id.ball_name);
        ballName.setText(item.getId());
        return contentView;
    }
}
