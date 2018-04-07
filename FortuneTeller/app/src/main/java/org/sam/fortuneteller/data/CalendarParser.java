package org.sam.fortuneteller.data;

import org.sam.fortuneteller.model.Consts;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sam on 2018/4/6.
 */
public class CalendarParser {

    //庚
    private static final int YearTopStart = 6;

    //申
    private static final int YearDownStart = 8;

    //丑
    private static final int HourDownStart = 1;


    private Map<Integer, Coin> monthStart = new HashMap<>();

    private SolarTerm solarTerm = new SolarTerm();


    public CalendarParser() {
        //甲、己 = 丙、寅
        monthStart.put(0, new Coin(2, 2));
        //乙、庚 = 戊、寅
        monthStart.put(1, new Coin(4, 2));
        //丙、辛 = 庚、寅
        monthStart.put(2, new Coin(6, 2));
        //丁、壬 = 壬、寅
        monthStart.put(3, new Coin(8, 2));
        //戊、癸 = 甲、寅
        monthStart.put(4, new Coin(0, 2));
    }

    public static String coinToPresent(Coin coin) {
        StringBuilder builder = new StringBuilder(2);
        if (coin.top >= 0) {
            builder.append(FortuneContent.TOP[coin.top]);
        }
        builder.append(FortuneContent.DOWN[coin.down]);
        return builder.toString();
    }

    public static String transformYear(int year) {
        Coin coin = transformYearToCoin(year);
        return coinToPresent(coin);
    }

    public static Coin transformYearToCoin(int year) {
        String yearStr = String.valueOf(year);
        int lastYearIndex = Integer.parseInt(yearStr.substring(3));
        int totalTopIndex = YearTopStart + lastYearIndex;
        int actualTopIndex = totalTopIndex % FortuneContent.TOP.length;

        int totalDownIndex = YearDownStart + year % 12;
        int actualDownIndex = totalDownIndex % FortuneContent.DOWN.length;

        return new Coin(actualTopIndex, actualDownIndex);
    }

    public Coin transformMonthToCoin(int year, int mon, int day) {
        Calendar[] solarTermCalendars = solarTerm.getSolarTermCalendars(year);
        Calendar matchedCal = null;
        int index = 0;
        for (;index < solarTermCalendars.length; ++index) {
            Calendar cal = solarTermCalendars[index];
            if (null == matchedCal) {
                matchedCal = cal;
                continue;
            }
            int month = cal.get(Calendar.MONTH);
            if (month == 0) {
                month = 12;
            }
            int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
            if (mon == month && day < dayOfMonth) {
                break;
            } else if (mon == month && day == dayOfMonth){
                matchedCal = cal;
                break;
            } else if (mon + 1 == month && dayOfMonth < day){
                break;
            } else {
                matchedCal = cal;
            }
        }
        Coin coin = transformYearToCoin(year);
        int monthIndex = (index - 2)/2;
        int yearIndex = coin.top % 5;
        Coin monthCoin = monthStart.get(yearIndex);
        return new Coin((monthCoin.top + monthIndex) % FortuneContent.TOP.length, (monthCoin.down + monthIndex) % FortuneContent.DOWN.length);
    }

    public String transformMonth(int year, int mon, int day) {
        Coin coin = transformMonthToCoin(year, mon, day);
        return coinToPresent(coin);
    }

    public Coin transformDayToCoin(int year, int mon, int day) {
        long[] longs = ChinaDate.calElement(year, mon, day);
        long ground = longs[5];
        int topIndex = (int)(ground % FortuneContent.TOP.length);
        int downIndex = (int)(ground % FortuneContent.DOWN.length);
        return new Coin(topIndex, downIndex);
    }

    public String transformDay(int year, int mon, int day) {
        Coin coin = transformDayToCoin(year, mon, day);
        return coinToPresent(coin);
    }

    public Coin transformHourToCoin(int hour) {
        if (hour > 24) {
            hour = hour - 24;
        }
        if (hour == 0) {
            hour = 24;
        }
        int downIndex = (HourDownStart + (hour - 1)/2) % FortuneContent.DOWN.length;
        return new Coin(-1, downIndex);
    }

    public String getNeighborDownPresent(int downIndex) {
        int down = downIndex % FortuneContent.DOWN.length;
        if (down % 2 == 0) {
            return FortuneContent.DOWN[down] + FortuneContent.DOWN[down + 1];
        } else {
            return FortuneContent.DOWN[down - 1] + FortuneContent.DOWN[down];
        }
    }

    public static class Coin {

        private final int top;

        private final int down;

        public Coin(int top, int down) {
            this.top = top;
            this.down = down;
        }

        public int getTop() {
            return top;
        }

        public int getDown() {
            return down;
        }

        @Override
        public String toString() {
            return "Coin{" +
                    "top=" + top +
                    ", down=" + down +
                    '}';
        }
    }

    public static void main(String[] args) {
        System.out.println(transformYear(2018));
        CalendarParser parser = new CalendarParser();
        System.out.println(parser.transformMonth(2018, 4, 7));
        System.out.println(parser.transformDay(2018, 4, 7));
        for (int i = 1; i <= 24; ++i) {
            System.out.println(i + "=" + coinToPresent(parser.transformHourToCoin(i)));
        }
        System.out.println(parser.getNeighborDownPresent(10));
        String digit = "010110";
        System.out.println(Integer.parseInt(digit, 2));
    }

}
