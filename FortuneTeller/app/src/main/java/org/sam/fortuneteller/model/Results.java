package org.sam.fortuneteller.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sam on 2017/12/2.
 */
public class Results {

    private String name;

    private final List<BallResult> balls;

    private String savedPath;


    public Results()
    {
        balls = new ArrayList<>();
        for (int i = 0; i < Consts.NUMBER_MAX_RED; ++i)
        {
            balls.add(new BallResult(BallColor.RED, i + 1, this));
        }
        for (int i = 0; i < Consts.NUMBER_MAX_BLUE; ++i)
        {
            balls.add(new BallResult(BallColor.BLUE, i + 1, this));
        }
    }

    public int size()
    {
        return balls.size();
    }


    public BallResult get(int index)
    {
        return balls.get(index);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BallResult> getBalls() {
        return balls;
    }

    public String getSavedPath() {
        return savedPath;
    }

    public void setSavedPath(String savedPath) {
        this.savedPath = savedPath;
    }
}
