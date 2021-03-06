package org.sam.fortuneteller.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import org.sam.fortuneteller.model.BallResult;
import org.sam.fortuneteller.model.Consts;
import org.sam.fortuneteller.model.Results;

import static org.sam.fortuneteller.model.Consts.COL_ID;

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
     *  CREATE DATE UNIQUE
     *  TEXT NAME
     *  INTEGER BALL_ID
     *  TEXT CONTENT
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Consts.TBL_RESULTS + " ("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Consts.COL_CREATE_DATE + " TEXT, "
                + Consts.COL_NAME + " TEXT, "
                + Consts.COL_BALL_ID + " TEXT, "
                + Consts.COL_CONTENT + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("new is " + newVersion + ", old is " + oldVersion);
    }

    public Results checkAndInsertResult (Results results) {
        String createDate = results.getCreateDate();
        Results existResult = loadBallResults(createDate);
        if  (null != existResult) {
            return existResult;
        }
        return insertBallResults(results);
    }

    private Results insertBallResults(Results results) {
        SQLiteDatabase db = getWritableDatabase();
        for (BallResult ballResult : results.getBalls()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Consts.COL_CREATE_DATE, results.getCreateDate());
            contentValues.put(Consts.COL_NAME, results.getName());
            contentValues.put(Consts.COL_BALL_ID, ballResult.getId());
            contentValues.put(Consts.COL_CONTENT, ballResult.getContent());
            db.insert(Consts.TBL_RESULTS, null, contentValues);
        }
        return results;
    }

    public int updateBallResult(BallResult ballResult) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Consts.COL_CONTENT, ballResult.getContent());
        return db.update(Consts.TBL_RESULTS, values,
                Consts.COL_CREATE_DATE + "= ? and " + Consts.COL_BALL_ID + "= ?",
                new String[]{ballResult.getCreateDate(), ballResult.getId()});
    }

    public Results loadBallResults(String key) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor cursor = readableDatabase.query(Consts.TBL_RESULTS,
                new String[]{Consts.COL_ID, Consts.COL_CREATE_DATE, Consts.COL_NAME, Consts.COL_BALL_ID, Consts.COL_CONTENT},
                Consts.COL_CREATE_DATE + " = ?",
                new String[]{key}, null, null, null);
        if (!cursor.moveToFirst()) {
            return null;
        }
        Results results = new Results();
        results.setCreateDate(getColText(cursor, Consts.COL_CREATE_DATE));
        results.setName(getColText(cursor, Consts.COL_NAME));
        do {
            int colId = getColInteger(cursor, Consts.COL_ID);
            String ballId = getColText(cursor, Consts.COL_BALL_ID);
            String content = getColText(cursor, Consts.COL_CONTENT);
            BallResult ballById = results.getBallById(ballId);
            if (null != ballById) {
                ballById.setColId(colId);
                ballById.setContent(content);
            }
        } while (cursor.moveToNext());
        return results;
    }

    private String getColText(Cursor cursor, String colName) {
        return  cursor.getString(cursor.getColumnIndex(colName));
    }

    private int getColInteger(Cursor cursor, String colName) {
        return  cursor.getInt(cursor.getColumnIndex(colName));
    }

}
