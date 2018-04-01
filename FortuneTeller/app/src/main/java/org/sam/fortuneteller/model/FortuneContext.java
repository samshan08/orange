package org.sam.fortuneteller.model;

import java.util.Random;

/**
 * Created by SAM on 2018/3/31.
 */
public class FortuneContext {

    private Random random = new Random();

    private int first = 1;

    private int second = 0;

    private int third = 1;

    private String[] e = { "　　　", "　　　", "　　　", "　　　", "　　　", "　　　" };

    private String[] f = { "　", "　", "　", "　", "　", "　" };

    public void doRandom() {
        first = random.nextInt(2);
        second = random.nextInt(2);
        third = random.nextInt(2);
}
}
