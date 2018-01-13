package org.sam.fortuneteller;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Sam on 2017/9/10.
 */
public class SixRandomController {

    private static final String[] result = { "老阴Ｘ", "少阳／", "少阴//", "老阳Ｏ" };

    private Random coinRd = new SecureRandom();


    public void randomNow(RandomResult result, int time)
    {
        int[] coinRds = new int[3];
        for (int i = 0; i < coinRds.length; ++i)
        {
            coinRds[i] = coinRd.nextInt(2);
        }

    }
}
