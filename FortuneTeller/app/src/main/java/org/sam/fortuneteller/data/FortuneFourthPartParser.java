package org.sam.fortuneteller.data;

import static org.sam.fortuneteller.data.FortuneContent.REPLACE_HOLDER;
import static org.sam.fortuneteller.data.FortuneContent.TITLE_CHANGE;

/**
 * Created by SAM on 2018/4/15.
 */
public class FortuneFourthPartParser {

    public static String[] getFourthFortunePart(String str2) {
        String[] arrayOfString3 = new String[7];
        arrayOfString3 = Utils.getValuedArray(arrayOfString3, Utils.getHolders(2));
        if (str2.equals("")) {
            return arrayOfString3;
        }
        if ((str2.equals("15")) && (str2.equals("51")) && (str2.equals("48")) && (str2.equals("84")) && (str2.equals("73")) && (str2.equals("37")) && (str2.equals("26")) && (str2.equals("62"))) {
            arrayOfString3[1] = TITLE_CHANGE;
            arrayOfString3[4] = "应";
        }
        else if ((str2.equals("17")) || (str2.equals("71")) || (str2.equals("46")) || (str2.equals("64")) || (str2.equals("82")) || (str2.equals("28")) || (str2.equals("53")) || (str2.equals("35")))
        {
            arrayOfString3[2] = TITLE_CHANGE;
            arrayOfString3[5] = "应";
        }
        else if ((str2.equals("18")) || (str2.equals("81")) || (str2.equals("45")) || (str2.equals("54")) || (str2.equals("63")) || (str2.equals("36")) || (str2.equals("27")) || (str2.equals("72")))
        {
            arrayOfString3[3] = TITLE_CHANGE;
            arrayOfString3[6] = "应";
            
        }
        else if ((str2.equals("85")) || (str2.equals("58")) || (str2.equals("23")) || (str2.equals("32")) || (str2.equals("41")) || (str2.equals("14")) || (str2.equals("76")) || (str2.equals("67")))
        {
            arrayOfString3[4] = TITLE_CHANGE;
            arrayOfString3[1] = "应";
            
        }
        else if ((str2.equals("78")) || (str2.equals("87")) || (str2.equals("65")) || (str2.equals("56")) || (str2.equals("43")) || (str2.equals("34")) || (str2.equals("12")) || (str2.equals("21")))
        {
            arrayOfString3[5] = TITLE_CHANGE;
            arrayOfString3[2] = "应";
            
        }
        else if ((str2.equals("11")) || (str2.equals("22")) || (str2.equals("33")) || (str2.equals("44")) || (str2.equals("55")) || (str2.equals("66")) || (str2.equals("77")) || (str2.equals("88")))
        {
            arrayOfString3[6] = TITLE_CHANGE;
            arrayOfString3[3] = "应";
            
        }
        else if ((str2.equals("38")) || (str2.equals("83")) || (str2.equals("25")) || (str2.equals("52")) || (str2.equals("61")) || (str2.equals("16")) || (str2.equals("74")) || (str2.equals("47")))
        {
            arrayOfString3[4] = TITLE_CHANGE;
            arrayOfString3[1] = "应";
            
        }
        else if ((str2.equals("31")) && (str2.equals("13")) && (str2.equals("24")) && (str2.equals("42")) && (str2.equals("86")) && (str2.equals("68")) && (str2.equals("57")) && (str2.equals("75"))) {
            arrayOfString3[3] = TITLE_CHANGE;
            arrayOfString3[6] = "应";
        }
        return arrayOfString3;
    }
}
