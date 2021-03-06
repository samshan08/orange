package org.sam.fortuneteller.data;

/**
 * Created by Sam on 2018/4/6.
 */
public class FortuneSecondPartParser {

    public static String parsePreValue(String paramString)
    {
        if (paramString.equals("")) {
            return "";
        }
        switch (Integer.parseInt(paramString)) {
            case 11:
                return "《乾为天》";
            case 15:
                return "《天风姤》";
            case 17:
                return "《天山遁》";
            case 18:
                return "《天地否》";
            case 58:
                return "《风地观》";
            case 78:
                return "《山地剥》";
            case 38:
                return "《火地晋》";
            case 31:
                return "《大　有》";
            case 22:
                return "《兑为泽》";
            case 26:
                return "《泽水困》";
            case 28:
                return "《泽地萃》";
            case 27:
                return "《泽山咸》";
            case 67:
                return "《水山蹇》";
            case 87:
                return "《地山谦》";
            case 47:
                return "《小　过》";
            case 42:
                return "《归　妹》";
            case 33:
                return "《离为火》";
            case 37:
                return "《火山旅》";
            case 35:
                return "《火风鼎》";
            case 36:
                return "《未　济》";
            case 76:
                return "《山水蒙》";
            case 56:
                return "《风水涣》";
            case 16:
                return "《天水讼》";
            case 13:
                return "《同　人》";
            case 44:
                return "《震为雷》";
            case 48:
                return "《雷地豫》";
            case 46:
                return "《雷水解》";
            case 45:
                return "《雷风恒》";
            case 85:
                return "《地风升》";
            case 65:
                return "《水风井》";
            case 25:
                return "《大　过》";
            case 24:
                return "《泽雷随》";
            case 55:
                return "《巽为风》";
            case 51:
                return "《小　畜》";
            case 53:
                return "《家　人》";
            case 54:
                return "《风雷益》";
            case 14:
                return "《无　妄》";
            case 34:
                return "《噬　嗑》";
            case 74:
                return "《山雷颐》";
            case 75:
                return "《山风蛊》";
            case 66:
                return "《坎为水》";
            case 62:
                return "《水泽节》";
            case 64:
                return "《水雷屯》";
            case 63:
                return "《既　济》";
            case 23:
                return "《泽火革》";
            case 43:
                return "《雷火丰》";
            case 83:
                return "《明　夷》";
            case 86:
                return "《地水师》";
            case 77:
                return "《艮为山》";
            case 73:
                return "《山火贲》";
            case 71:
                return "《大　畜》";
            case 72:
                return "《山泽损》";
            case 32:
                return "《火泽睽》";
            case 12:
                return "《天泽履》";
            case 52:
                return "《中　孚》";
            case 57:
                return "《风山渐》";
            case 88:
                return "《坤为地》";
            case 84:
                return "《地雷复》";
            case 82:
                return "《地泽临》";
            case 81:
                return "《地天泰》";
            case 41:
                return "《大　壮》";
            case 21:
                return "《泽天夬》";
            case 61:
                return "《水天需》";
            case 68:
                return "《水地比》";
            case 19:
            case 20:
            case 29:
            case 30:
            case 39:
            case 40:
            case 49:
            case 50:
            case 59:
            case 60:
            case 69:
            case 70:
            case 79:
            case 80:
            default:
                return "";
        }
    }

    public static String getJoinPart(int arrayIndex, int strIndex) {
        return FortuneContent.FORTUNE_PARTS[arrayIndex].substring(strIndex - 1, strIndex);
    }

    public static String[] getSecondFortunePart(String paramString) {
        String[] arrayOfString2 = new String[7];
        if (paramString.equals("")) {
            return Utils.getEmptyArray(arrayOfString2);
        }
        return internalGetSecondFortunePart(
                Integer.parseInt(paramString.substring(0, 1)),
                Integer.parseInt(paramString.substring(1, 2)),
                        arrayOfString2);
    }

    public static String[] internalGetSecondFortunePart(int first, int second, String[] arrayOfString2) {
        arrayOfString2[0] = "";
        arrayOfString2[6] = getJoinPart(first, 6);
        arrayOfString2[5] = getJoinPart(first, 5);
        arrayOfString2[4] = getJoinPart(first, 4);
        arrayOfString2[3] = getJoinPart(second, 3);
        arrayOfString2[2] = getJoinPart(second, 2);
        arrayOfString2[1] = getJoinPart(second, 1);
        return arrayOfString2;
    }

    public static void main(String[] args) {
        for (int i = 1; i < 100; ++i) {
            String value = parsePreValue(String.valueOf(i));
            if (value.length() == 0) {
                System.out.println(i);
            }
        }
    }
}
