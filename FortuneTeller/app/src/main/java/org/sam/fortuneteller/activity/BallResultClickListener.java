package org.sam.fortuneteller.activity;

import org.sam.fortuneteller.model.BallResult;

/**
 * Created by SAM on 2018/1/16.
 */
public interface BallResultClickListener {

    /**
     * preform when the result item is clicked
     * @param ballResult
     */
    void itemClick(BallResult ballResult);
}
