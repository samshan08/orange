package org.sam.fortuneteller.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.sam.fortuneteller.R;
import org.sam.fortuneteller.adapter.TellingFortuneListener;
import org.sam.fortuneteller.data.CalendarParser;
import org.sam.fortuneteller.data.FortuneContent;
import org.sam.fortuneteller.data.FortuneParser;
import org.sam.fortuneteller.model.BallResult;
import org.sam.fortuneteller.model.Consts;

import java.util.Calendar;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.*;

import static org.sam.fortuneteller.model.Consts.TOKEN_SEP;

/**
 * 查看占卜结果
 * Created by Sam on 2017/12/2.
 */
public class ResultsDetailActivity extends AppCompatActivity{

    private BallResult ballResult;

    private ScheduledExecutorService services;

    private TellingFortuneListener tellingFortuneListener = null;

    private ScheduledFuture<?> scheduledFuture = null;

    private Handler calFortuneHandler;

    private Random random = new Random();

    @Override
    protected void onStart() {
        super.onStart();
        services = new ScheduledThreadPoolExecutor(1);
        calFortuneHandler = new Handler() {

            private CalendarParser parser = new CalendarParser();

            @Override
            public void handleMessage(Message msg) {
                int what = msg.what;
                Calendar current = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
                int year = current.get(Calendar.YEAR);
                int month = current.get(Calendar.MONTH) + 1;
                int day = current.get(Calendar.DAY_OF_MONTH);
                int hour = current.get(Calendar.HOUR_OF_DAY);
                int minute = current.get(Calendar.MINUTE);
                CalendarParser.Coin yearCoin = CalendarParser.transformYearToCoin(year);
                String neighboringYear = parser.getNeighborDownPresent(yearCoin.getDown());
                CalendarParser.Coin monthCoin = parser.transformMonthToCoin(year, month, day);
                CalendarParser.Coin dayCoin = parser.transformDayToCoin(year, month, day);
                CalendarParser.Coin hourCoin = parser.transformHourToCoin(hour);
                String timeText = String.format(FortuneContent.TIME_TEXT, year, month, day, hour, minute);
                String yearPresent = CalendarParser.coinToPresent(yearCoin);
                String monthPresent = CalendarParser.coinToPresent(monthCoin);
                String dayPresent = CalendarParser.coinToPresent(dayCoin);
                String hourPresent = CalendarParser.coinToPresent(hourCoin);
                String nongliText = String.format(Locale.CHINA, FortuneContent.NONG_TIME_TEXT,
                        yearPresent,
                        monthPresent,
                        dayPresent,
                        neighboringYear,
                        hourPresent,
                        what);

                String codeStr = String.valueOf(what);
                String firstToken = FortuneParser.parsePreValue(codeStr.substring(0, 2));
                String secondToken = null;
                switch (codeStr.length()) {
                    case 2:{
                        break;
                    }
                    case 3: {
                        StringBuilder builder = new StringBuilder();
                        builder.append(Integer.parseInt(codeStr.substring(2)));
                        builder.append(random.nextInt(8) + 1);
                        int secondTokenCode = Integer.parseInt(builder.toString());
                        secondToken = FortuneParser.parsePreValue(String.valueOf(secondTokenCode));
                        break;
                    }
                    default: {
                        int firstIndex = random.nextInt(codeStr.length() - 2) + 2;
                        int secondIndex = random.nextInt(codeStr.length() - 2) + 2;
                        int firstTokenIndex = Integer.parseInt(codeStr.substring(firstIndex, firstIndex + 1));
                        int secondITokenIndex = Integer.parseInt(codeStr.substring(secondIndex, secondIndex + 1));
                        StringBuilder builder = new StringBuilder();
                        builder.append(firstTokenIndex);
                        builder.append(secondITokenIndex);
                        int secondTokenCode = Integer.parseInt(builder.toString());
                        secondToken = FortuneParser.parsePreValue(String.valueOf(secondTokenCode));
                        break;
                    }
                }
                StringBuilder tokenText = new StringBuilder(TOKEN_SEP + firstToken + TOKEN_SEP);
                if (null != secondToken) {
                    tokenText.append(secondToken).append(TOKEN_SEP);
                }
                tokenText.append("六神");
                TextView tellFortuneText = (TextView) findViewById(R.id.tellContent);
                tellFortuneText.setText(timeText + "\r\n" + nongliText + "\r\n" + tokenText);
            }
        };
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
                        cancelFuture();
                    }
                })
                .setNegativeButton(R.string.btn_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cancelFuture();
                    }
                }).show();
    }

    private void confirmFortuneResult(DialogInterface dialog, int which) {
        String fortuneCode = tellingFortuneListener.getFortuneCode();
        calFortuneHandler.sendEmptyMessageDelayed(Integer.parseInt(fortuneCode), 10L);
    }

}
