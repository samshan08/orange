package org.sam.fortuneteller.model;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Sam on 2017/12/2.
 */
public class Results {

    private String createDate;

    private String name;

    private final List<BallResult> balls;

    private final Map<String, BallResult> ballMaps;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public Results()
    {
        createDate = sdf.format(new Date());
        balls = new ArrayList<>();
        ballMaps = new HashMap<>();
        BallResult ballResult;
        for (int i = 0; i < Consts.NUMBER_MAX_RED; ++i)
        {
            ballResult = new BallResult(createDate, BallColor.RED, i + 1);
            balls.add(ballResult);
            ballMaps.put(ballResult.getId(), ballResult);
        }
        for (int i = 0; i < Consts.NUMBER_MAX_BLUE; ++i)
        {
            ballResult = new BallResult(createDate, BallColor.BLUE, i + 1);
            balls.add(ballResult);
            ballMaps.put(ballResult.getId(), ballResult);
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

    public BallResult getBallById(String ballId) {
        return ballMaps.get(ballId);
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
