package org.sam.fortuneteller;

import lombok.Getter;

/**
 * Created by Sam on 2017/9/17.
 */
public class RandomResult {

    private final int[] coinResults;

    private final int[] representResults;

    public RandomResult()
    {
        coinResults = new int[6];

        representResults = new int[6];
    }

    public int[] getCoinResults() {
        return coinResults;
    }

    public int[] getRepresentResults() {
        return representResults;
    }
}
