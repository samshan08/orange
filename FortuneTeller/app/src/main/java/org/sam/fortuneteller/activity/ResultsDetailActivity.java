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
import org.sam.fortuneteller.adapter.TellingFortuneListener;
import org.sam.fortuneteller.model.BallResult;
import org.sam.fortuneteller.model.Consts;

import java.util.concurrent.*;

/**
 * 查看占卜结果
 * Created by Sam on 2017/12/2.
 */
public class ResultsDetailActivity extends AppCompatActivity{

    private BallResult ballResult;

    private ScheduledExecutorService services;

    private TellingFortuneListener tellingFortuneListener = null;

    private ScheduledFuture<?> scheduledFuture = null;

    @Override
    protected void onStart() {
        super.onStart();
        services = new ScheduledThreadPoolExecutor(1);
    }

    @Override
    protected void onStop() {
        super.onStop();
        cancelFuture();
        services.shutdownNow();
        services = null;
    }

    private void cancelFuture() {
        if (null != scheduledFuture) {
            scheduledFuture.cancel(true);
            scheduledFuture = null;
        }
        tellingFortuneListener = null;
    }

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

    private void scheduleTask() {
        if (null != scheduledFuture) {
            scheduledFuture.cancel(true);
        }
        scheduledFuture = services.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                tellingFortuneListener.doRandom();
            }
        }, 1000, 10, TimeUnit.MILLISECONDS);
    }

    private void doTellFortune(View view) {
        View localView = LayoutInflater.from(this).inflate(R.layout.yaogua, null);
        tellingFortuneListener = new TellingFortuneListener(localView);
        localView.findViewById(R.id.btyao).setOnTouchListener(tellingFortuneListener);
        scheduleTask();
        new AlertDialog.Builder(this).
                setTitle(R.string.btn_yaoGua).setCancelable(false).setView(localView)
                .setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        confirmFortuneResult(dialog, which);
                    }
                })
                .setNeutralButton(R.string.btn_reYaoGua, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        reloadFortuneResult(dialog, which);
                    }
                })
                .setNegativeButton(R.string.btn_cancel, new DialogInterface.OnClickListener() {
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
        cancelFuture();
    }
}
