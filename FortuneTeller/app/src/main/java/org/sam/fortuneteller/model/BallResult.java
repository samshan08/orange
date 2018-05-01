package org.sam.fortuneteller.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 * @author Sam
 * @date 2017/12/2
 */
public class BallResult implements Parcelable {

    private final BallColor color;

    private final int number;

    private final String createDate;

    private int colId;

    private final String id;

    private String content;

    public BallResult(String createDate, BallColor color, int number) {
        this.color = color;
        this.number = number;
        this.createDate = createDate;
        id = color.name() + "-" + number;
        content = "";
    }

    public static final Parcelable.Creator<BallResult> CREATOR = new Creator<BallResult>() {

        @Override
        public BallResult createFromParcel(Parcel source) {
            String createDate = source.readString();
            String colorStr = source.readString();
            int number = source.readInt();
            String content = source.readString();
            BallColor color = BallColor.valueOf(colorStr);
            BallResult result = new BallResult(createDate, color, number);
            result.setContent(content);
            return result;
        }

        @Override
        public BallResult[] newArray(int size) {
            return new BallResult[size];
        }
    };

    public BallColor getColor() {
        return color;
    }

    public int getNumber() {
        return number;
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

    @Override
    public String toString() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(createDate);
        dest.writeString(color.name());
        dest.writeInt(number);
        dest.writeString(content);
    }

    public int getColId() {
        return colId;
    }

    public void setColId(int colId) {
        this.colId = colId;
    }

    public String getCreateDate() {
        return createDate;
    }
}
