package org.sam.fortuneteller.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.sam.fortuneteller.R;
import org.sam.fortuneteller.model.BallResult;
import org.sam.fortuneteller.model.Consts;

/**
 * 查看占卜结果
 * Created by Sam on 2017/12/2.
 */
public class ResultsDetailActivity extends AppCompatActivity{

    private BallResult ballResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ball_detail);
        ballResult = getIntent().getExtras().getParcelable(Consts.KEY_RESULT_ITEM_KEY);
        TextView textView = (TextView) findViewById(R.id.tellContent);
        textView.setText(ballResult.getContent());
        Button btnTell = (Button) findViewById(R.id.btn_tell);
        btnTell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doTellFortune(v);
            }
        });
    }

    public void doTellFortune(View view) {
        //TODO:
    }

}
