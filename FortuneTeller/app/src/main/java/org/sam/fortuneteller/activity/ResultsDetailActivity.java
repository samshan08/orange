package org.sam.fortuneteller.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
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
        ballResult = getIntent().getParcelableExtra(Consts.KEY_RESULT_ITEM_KEY);
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

    private void doTellFortune(View view) {
        View localView = LayoutInflater.from(this).inflate(R.layout.yaogua, null);
        new AlertDialog.Builder(this).
                setTitle(R.string.btn_yaoGua).setCancelable(false).setView(localView)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        confirmFortuneResult(dialog, which);
                    }
                })
                .setNeutralButton("重新摇卦", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        reloadFortuneResult(dialog, which);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cancelFortuneResult(dialog, which);
                    }
                }).show();
    }

    private void confirmFortuneResult(DialogInterface dialog, int which) {
        //TODO
    }

    private void reloadFortuneResult(DialogInterface dialog, int which) {
        //TODO
    }

    private void cancelFortuneResult(DialogInterface dialog, int which) {
        //TODO
    }
}
