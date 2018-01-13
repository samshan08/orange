package org.sam.fortuneteller.model;

import lombok.Data;

/**
 * Created by Sam on 2017/12/2.
 */
public class BallResult {

    private final BallColor color;

    private final int number;

    private final Results parent;

    private final String id;

    private String content;

    public BallResult(BallColor color, int number, Results parent) {
        this.color = color;
        this.number = number;
        this.parent = parent;
        id = color.name() + "-" + number;
    }

    public BallColor getColor() {
        return color;
    }

    public int getNumber() {
        return number;
    }

    public Results getParent() {
        return parent;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
