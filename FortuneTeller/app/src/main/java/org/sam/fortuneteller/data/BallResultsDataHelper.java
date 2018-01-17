package org.sam.fortuneteller.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import org.sam.fortuneteller.model.Consts;

/**
 * Created by SAM on 2018/1/16.
 */
public class BallResultsDataHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "ballResults";

    private static final int DB_VERSION = 1;


    public BallResultsDataHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    /**
     * DB structure is shown as below:
     *  TEXT NAME UNIQUE
     *  INTEGER BALL_ID
     *  TEXT CONTENT
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Consts.TABLE_NAME + " ("
                + "NAME TEXT PRIMARY KEY. "
                + "BALL_ID INTEGER, "
                + "CONTENT TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
