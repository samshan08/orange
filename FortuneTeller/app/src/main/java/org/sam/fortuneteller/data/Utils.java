package org.sam.fortuneteller.data;

import static org.sam.fortuneteller.data.FortuneContent.REPLACE_HOLDER;

/**
 * Created by SAM on 2018/4/15.
 */
public class Utils {

    public static String[] getEmptyArray(String[] arrayOfString5) {
        return getValuedArray(arrayOfString5, "");
    }

    public static String[] getValuedArray(String[] arrayOfString5, String value) {
        for (int i = 0; i < arrayOfString5.length; ++i) {
            if (i == 0) {
                arrayOfString5[i] = "";
            } else {
                arrayOfString5[i] = value;
            }
        }
        return arrayOfString5;
    }


    public static String getHolders(int count) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; ++i) {
            builder.append(REPLACE_HOLDER);
        }
        return builder.toString();
    }
}
