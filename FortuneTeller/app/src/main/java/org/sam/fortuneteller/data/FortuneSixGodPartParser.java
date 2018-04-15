package org.sam.fortuneteller.data;

/**
 * Created by SAM on 2018/4/15.
 */
public class FortuneSixGodPartParser {

    public static String[] getSixGod(String dayTop) {
        String[] arrayOfString4 = new String[7];
        arrayOfString4[0] = "";
        if ((dayTop.equals("甲")) || (dayTop.equals("乙")))
        {
            arrayOfString4[1] = "龙";
            arrayOfString4[2] = "雀";
            arrayOfString4[3] = "勾";
            arrayOfString4[4] = "蛇";
            arrayOfString4[5] = "虎";
            arrayOfString4[6] = "玄";
        }
        if ((dayTop.equals("丙")) || (dayTop.equals("丁")))
        {
            arrayOfString4[1] = "雀";
            arrayOfString4[2] = "勾";
            arrayOfString4[3] = "蛇";
            arrayOfString4[4] = "虎";
            arrayOfString4[5] = "玄";
            arrayOfString4[6] = "龙";
        }
        if (dayTop.equals("戊"))
        {
            arrayOfString4[1] = "勾";
            arrayOfString4[2] = "蛇";
            arrayOfString4[3] = "虎";
            arrayOfString4[4] = "玄";
            arrayOfString4[5] = "龙";
            arrayOfString4[6] = "雀";
        }
        if (dayTop.equals("己"))
        {
            arrayOfString4[1] = "蛇";
            arrayOfString4[2] = "虎";
            arrayOfString4[3] = "玄";
            arrayOfString4[4] = "龙";
            arrayOfString4[5] = "雀";
            arrayOfString4[6] = "勾";
        }
        if ((dayTop.equals("庚")) || (dayTop.equals("辛")))
        {
            arrayOfString4[1] = "虎";
            arrayOfString4[2] = "玄";
            arrayOfString4[3] = "龙";
            arrayOfString4[4] = "雀";
            arrayOfString4[5] = "勾";
            arrayOfString4[6] = "蛇";
        }
        if ((dayTop.equals("壬")) || (dayTop.equals("癸")))
        {
            arrayOfString4[1] = "玄";
            arrayOfString4[2] = "龙";
            arrayOfString4[3] = "雀";
            arrayOfString4[4] = "勾";
            arrayOfString4[5] = "蛇";
            arrayOfString4[6] = "虎";
        }
        return arrayOfString4;
    }

}
