package org.sam.fortuneteller.adapter;

import org.sam.fortuneteller.R;

import java.security.SecureRandom;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Sam on 2017/9/10.
 */
public class SixRandomController {

    private final Map<String, String> roundToResult = new HashMap<>();

    private final Map<Integer, String> intToRound = new HashMap<>();

    private final Map<Integer, Integer> intToResourceId = new HashMap<>();

    private Random coinRd = new SecureRandom();

    private int[] lastRandomResult = null;

    public SixRandomController() {
        roundToResult.put("阳阳阳", "老阳Ｏ");
        roundToResult.put("阳阳阴", "少阴//");
        roundToResult.put("阳阴阳", "少阴//");
        roundToResult.put("阳阴阴", "少阳／");
        roundToResult.put("阴阳阳", "少阴//");
        roundToResult.put("阴阳阴", "少阳／");
        roundToResult.put("阴阴阳", "少阳／");
        roundToResult.put("阴阴阴", "老阴Ｘ");

        intToRound.put(0, "阴");
        intToRound.put(1, "阳");

        intToResourceId.put(0, R.raw.z0);
        intToResourceId.put(1, R.raw.z1);
    }

    public synchronized int[] getLastRandomResult() {
        return lastRandomResult;
    }

    public int[] randomNow() {
        int[] coinRds = new int[3];
        for (int i = 0; i < coinRds.length; ++i)
        {
            coinRds[i] = coinRd.nextInt(2);
        }
        synchronized (this) {
            lastRandomResult = coinRds;
        }
        return coinRds;
    }

    public String toRoundResult(int[] coinRds) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < coinRds.length; ++i)
        {
            builder.append(intToRound.get(coinRds[i]));
        }
        return builder.toString();
    }

    public String toActualResult(int[] coinRds) {
        return roundToResult.get(toRoundResult(coinRds));
    }

    public int[] toResourceIds(int[] coinRds) {
        int[] resIds = new int[3];
        for (int i = 0; i < coinRds.length; ++i)
        {
            resIds[i] = intToResourceId.get(coinRds[i]);
        }
        return resIds;
    }

    public static int compoundFromResult(int[] values) {
        int ret = 0;
        for (int i = 0; i < values.length; ++i) {
            ret = ret << 1;
            ret = ret | values[i];
        }
        return ret;
    }

    public static int[] compoundToResult(int value) {
        int[] ret = new int[3];
        for (int i = ret.length - 1; i >= 0; --i) {
            ret[i] = value & 1;
            value = value >> 1;
        }
        return ret;
    }

}
