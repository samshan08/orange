package org.sam.fortuneteller.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import org.sam.fortuneteller.R;
import org.sam.fortuneteller.data.FortuneContent;
import org.sam.fortuneteller.model.Consts;
import org.sam.fortuneteller.model.FortuneContext;

import java.util.Random;

/**
 * Created by SAM on 2018/3/31.
 */
public class TellingFortuneListener implements View.OnTouchListener {

    private final View fortune;

    private final ImageView[] imgViews;

    private final TextView[] textViews;

    private final Button btnYao;

    private final Handler renderHandler;

    private final SixRandomController controller;

    private volatile boolean startFortune = false;

    private String[] results = new String[Consts.MAX_FORTUNE_ROUND];

    private int[][] rdnsResults = new int[Consts.MAX_FORTUNE_ROUND][];

    private int currentRound = 0;

    private String fortuneCode;

    private final String resultTmpl;

    public TellingFortuneListener(View fortune) {
        this.fortune = fortune;
        Context context = fortune.getContext();
        resultTmpl = context.getString(R.string.txtDlgResult);

        imgViews = new ImageView[3];
        imgViews[0] = (ImageView) fortune.findViewById(R.id.imageView1);
        imgViews[1] = (ImageView) fortune.findViewById(R.id.imageView2);
        imgViews[2] = (ImageView) fortune.findViewById(R.id.imageView3);

        textViews = new TextView[3];
        textViews[0] = (TextView) fortune.findViewById(R.id.yaotext2);
        textViews[1] = (TextView) fortune.findViewById(R.id.yaotext3);
        textViews[2] = (TextView) fortune.findViewById(R.id.yaotext4);

        btnYao = (Button) fortune.findViewById(R.id.btyao);

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
        initResults();
    }

    private void initResults() {
        currentRound = 0;
        for (int i = 0; i < Consts.MAX_FORTUNE_ROUND; ++i) {
            results[i] = "            ";
            rdnsResults[i] = new int[3];
        }
    }

    public String getFortuneCode() {
        return fortuneCode;
    }

    private void repaintResult() {
        String tmpl = resultTmpl;
        for (int i = 0; i < Consts.MAX_FORTUNE_ROUND; ++i) {
            tmpl = tmpl.replaceAll(String.valueOf(i), results[i]);
        }
        String[] values = tmpl.split(";");
        for (int i = 0; i < 3; ++i) {
            textViews[i].setText(values[i]);
        }
        if (currentRound < 6) {
            btnYao.setText(FortuneContent.TIMES[currentRound]);
        } else {
            fortuneCode = calFortuneCode();
            String resultText = String.format(FortuneContent.CODE_TEXT, fortuneCode);
            btnYao.setText(resultText);
        }
    }

    private String calFortuneCode() {
        //cal top
        int oldCount = 0;
        for (int i = 0; i < 6; i++) {
            if (isFullResult(i)) {
                oldCount++;
            }
        }
        int bitCount = 2 + oldCount;
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < bitCount; ++i) {
            int rdn = random.nextInt(8) + 1;
            builder.append(rdn);
        }
        return builder.toString();
    }

    private boolean isFullResult(int index) {
        //000.or 111
        int[] result = rdnsResults[index];
        int first = result[0];
        for (int i = 1; i < result.length; ++i) {
            if (result[i] != first) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if (currentRound >= Consts.MAX_FORTUNE_ROUND) {
            return false;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:  {
                startFortune = false;
                int[] lastRandomResult = controller.getLastRandomResult();
                if (null == lastRandomResult) {
                    break;
                }
                String actualResult = controller.toActualResult(lastRandomResult);
                results[currentRound] = actualResult;
                rdnsResults[currentRound] = lastRandomResult;
                currentRound++;
                repaintResult();
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
