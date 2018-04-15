package org.sam.fortuneteller.adapter;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import org.sam.fortuneteller.data.*;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by SAM on 2018/4/15.
 */
public class CalFortuneHandler extends Handler {

    private final TextView tellFortuneText;

    private CalendarParser parser = new CalendarParser();

    public CalFortuneHandler(TextView tellFortuneText) {
        this.tellFortuneText = tellFortuneText;
    }

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
        String firstValue, secodeValue = "";
        firstValue = codeStr.substring(0, 2);
        String firstToken = FortuneSecondPartParser.parsePreValue(firstValue);
        String secondToken = null;
        switch (codeStr.length()) {
            case 2: {
                break;
            }
            case 3: {
                StringBuilder builder = new StringBuilder();
                builder.append(codeStr.substring(2, 3));
                builder.append(codeStr.substring(0, 1));
                secodeValue = builder.toString();
                int secondTokenCode = Integer.parseInt(secodeValue);
                secondToken = FortuneSecondPartParser.parsePreValue(String.valueOf(secondTokenCode));
                break;
            }
            default: {
                int firstTokenIndex = Integer.parseInt(codeStr.substring(3, 4));
                int secondITokenIndex = Integer.parseInt(codeStr.substring(2, 3));
                StringBuilder builder = new StringBuilder();
                builder.append(firstTokenIndex);
                builder.append(secondITokenIndex);
                secodeValue = builder.toString();
                int secondTokenCode = Integer.parseInt(secodeValue);
                secondToken = FortuneSecondPartParser.parsePreValue(String.valueOf(secondTokenCode));
                break;
            }
        }
        StringBuilder tokenText = new StringBuilder(Utils.getHolders(2) + firstToken + Utils.getHolders(4));
        if (null != secondToken) {
            tokenText.append(secondToken).append(Utils.getHolders(4));
        }
        tokenText.append("六神");
        FortuneFirstPartParser.FirstPart firstPart = FortuneFirstPartParser.getFirstFortunePart(firstValue);
        String[] firstCol = firstPart.getArrays();
        String[] secondCol = FortuneSecondPartParser.getSecondFortunePart(firstValue);
        String[] thirdCol = FortuneThirdPartParser.parseThirdFortunePart(firstValue);
        String[] forthCol = FortuneFourthPartParser.getFourthFortunePart(firstValue);
        String[] fifthCol = null;
        String[] sixthCol = null;
        if (secodeValue.length() > 0) {
            fifthCol = FortuneSecondPartParser.getSecondFortunePart(secodeValue);
            sixthCol = FortuneThirdPartParser.parseThirdFortunePart(secodeValue);
        }
        String[] seventhCol = FortuneSixGodPartParser.getSixGod(dayPresent.substring(0, 1));
        StringBuilder builder = new StringBuilder();
        builder.append(timeText + "\r\n" + nongliText + "\r\n" + tokenText + "\r\n");
        for (int i = firstCol.length - 1; i >= 0; --i) {
            builder.append(firstCol[i]).append(secondCol[i]);
            String firstSym = thirdCol[i];
            String secondSym = "";
            if (sixthCol != null) {
                secondSym = sixthCol[i];
                if (FortuneContent.SYM_SLASH.equals(firstSym) && FortuneContent.SYM_DOT.equals(secondSym)) {
                    firstSym = FortuneContent.SYM_CIRCLE;
                } else if (FortuneContent.SYM_DOT.equals(firstSym) && FortuneContent.SYM_SLASH.equals(secondSym)) {
                    firstSym = FortuneContent.SYM_CROSS;
                }
            }
            builder.append(firstSym);
            String title = forthCol[i];
            if (FortuneContent.TITLE_CHANGE.equals(title)) {
                title = firstPart.getHeader();
            }
            builder.append(title);
            if (fifthCol != null) {
                builder.append(Utils.getHolders(6));
                builder.append(fifthCol[i]);
                builder.append(secondSym);
                builder.append(Utils.getHolders(4));
            }
            builder.append(Utils.getHolders(4));
            builder.append(seventhCol[i]);
            builder.append("\r\n");
        }
        builder.append("分析：\n");
        tellFortuneText.setText(builder.toString());
    }
}
