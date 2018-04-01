package org.sam.fortuneteller.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.ImageView;
import org.sam.fortuneteller.R;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by SAM on 2018/3/31.
 */
public class TellingFortuneListener implements View.OnTouchListener {

    private final View fortune;

    private final ImageView[] imgViews;

    private final Handler renderHandler;

    private final SixRandomController controller;

    private volatile boolean startFortune = false;

    public TellingFortuneListener(View fortune) {
        this.fortune = fortune;
        Context context = fortune.getContext();
        System.out.println(context);
        imgViews = new ImageView[3];
        imgViews[0] = (ImageView) fortune.findViewById(R.id.imageView1);
        imgViews[1] = (ImageView) fortune.findViewById(R.id.imageView2);
        imgViews[2] = (ImageView) fortune.findViewById(R.id.imageView3);

        renderHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                int[] rdns = SixRandomController.compoundToResult(msg.what);
                int[] resIds = controller.toResourceIds(rdns);
                for (int i = 0; i < resIds.length; ++i) {
                    imgViews[i].setImageResource(resIds[i]);
                }
            }
        };
        controller = new SixRandomController();

    }


    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:  {
                startFortune = false;
                break;
            }
            case MotionEvent.ACTION_DOWN:  {
                startFortune = true;
                break;
            }
            default:
                break;
        }
        return false;
    }

    public void doRandom() {
        if (!startFortune) {
            return;
        }
        int[] rdns = controller.randomNow();
        renderHandler.sendEmptyMessageDelayed(SixRandomController.compoundFromResult(rdns), 10L);
    }

}
