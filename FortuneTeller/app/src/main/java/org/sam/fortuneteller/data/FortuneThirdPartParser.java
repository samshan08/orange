package org.sam.fortuneteller.data;

/**
 * Created by SAM on 2018/4/15.
 */
public class FortuneThirdPartParser {

    public static String[] parseThirdFortunePart(String paramString)
    {
        String[] arrayOfString3 = new String[7];
        if (paramString.equals("")) {
            return Utils.getEmptyArray(arrayOfString3);
        }
        switch (Integer.parseInt(paramString)) {
            case 11:
                return Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_SLASH);
            case 15:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_SLASH);
                arrayOfString3[1] = FortuneContent.SYM_DOT;
                return arrayOfString3;
            case 17:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_SLASH);
                arrayOfString3[2] = FortuneContent.SYM_DOT;
                arrayOfString3[1] = FortuneContent.SYM_DOT;
                return arrayOfString3;
            case 18:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_SLASH);
                arrayOfString3[3] = FortuneContent.SYM_DOT;
                arrayOfString3[2] = FortuneContent.SYM_DOT;
                arrayOfString3[1] = FortuneContent.SYM_DOT;
                return arrayOfString3;
            case 58:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_SLASH);
                arrayOfString3[4] = FortuneContent.SYM_DOT;
                arrayOfString3[3] = FortuneContent.SYM_DOT;
                arrayOfString3[2] = FortuneContent.SYM_DOT;
                arrayOfString3[1] = FortuneContent.SYM_DOT;
                return arrayOfString3;
            case 78:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_SLASH);
                arrayOfString3[5] = FortuneContent.SYM_DOT;
                arrayOfString3[4] = FortuneContent.SYM_DOT;
                arrayOfString3[3] = FortuneContent.SYM_DOT;
                arrayOfString3[2] = FortuneContent.SYM_DOT;
                arrayOfString3[1] = FortuneContent.SYM_DOT;
                return arrayOfString3;
            case 38:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_SLASH);
                arrayOfString3[5] = FortuneContent.SYM_DOT;
                arrayOfString3[3] = FortuneContent.SYM_DOT;
                arrayOfString3[2] = FortuneContent.SYM_DOT;
                arrayOfString3[1] = FortuneContent.SYM_DOT;
                return arrayOfString3;
            case 31:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_SLASH);
                arrayOfString3[5] = FortuneContent.SYM_DOT;
                return arrayOfString3;
            case 22:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_SLASH);
                arrayOfString3[5] = FortuneContent.SYM_DOT;
                return arrayOfString3;
            case 26:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_SLASH);
                arrayOfString3[6] = FortuneContent.SYM_DOT;
                arrayOfString3[3] = FortuneContent.SYM_DOT;
                arrayOfString3[1] = FortuneContent.SYM_DOT;
                return arrayOfString3;
            case 28:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_SLASH);
                arrayOfString3[6] = FortuneContent.SYM_DOT;
                arrayOfString3[3] = FortuneContent.SYM_DOT;
                arrayOfString3[2] = FortuneContent.SYM_DOT;
                arrayOfString3[1] = FortuneContent.SYM_DOT;
                return arrayOfString3;
            case 27:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_SLASH);
                arrayOfString3[6] = FortuneContent.SYM_DOT;
                arrayOfString3[2] = FortuneContent.SYM_DOT;
                arrayOfString3[1] = FortuneContent.SYM_DOT;
                return arrayOfString3;
            case 67:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_SLASH);
                arrayOfString3[6] = FortuneContent.SYM_DOT;
                arrayOfString3[4] = FortuneContent.SYM_DOT;
                arrayOfString3[2] = FortuneContent.SYM_DOT;
                arrayOfString3[1] = FortuneContent.SYM_DOT;
                return arrayOfString3;
            case 87:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_DOT);
                arrayOfString3[3] = FortuneContent.SYM_SLASH;
                return arrayOfString3;
            case 47:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_DOT);
                arrayOfString3[4] = FortuneContent.SYM_SLASH;
                arrayOfString3[3] = FortuneContent.SYM_SLASH;
                return arrayOfString3;
            case 42:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_SLASH);
                arrayOfString3[6] = FortuneContent.SYM_DOT;
                arrayOfString3[5] = FortuneContent.SYM_DOT;
                arrayOfString3[1] = FortuneContent.SYM_DOT;
                return arrayOfString3;
            case 33:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_SLASH);
                arrayOfString3[5] = FortuneContent.SYM_DOT;
                arrayOfString3[2] = FortuneContent.SYM_DOT;
                return arrayOfString3;
            case 37:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_SLASH);
                arrayOfString3[5] = FortuneContent.SYM_DOT;
                arrayOfString3[2] = FortuneContent.SYM_DOT;
                arrayOfString3[1] = FortuneContent.SYM_DOT;
                return arrayOfString3;
            case 35:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_SLASH);
                arrayOfString3[5] = FortuneContent.SYM_DOT;
                arrayOfString3[1] = FortuneContent.SYM_DOT;
                return arrayOfString3;
            case 36:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_SLASH);
                arrayOfString3[5] = FortuneContent.SYM_DOT;
                arrayOfString3[3] = FortuneContent.SYM_DOT;
                arrayOfString3[1] = FortuneContent.SYM_DOT;
                return arrayOfString3;
            case 76:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_DOT);
                arrayOfString3[6] = FortuneContent.SYM_SLASH;
                arrayOfString3[2] = FortuneContent.SYM_SLASH;
                return arrayOfString3;
            case 56:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_SLASH);
                arrayOfString3[4] = FortuneContent.SYM_DOT;
                arrayOfString3[3] = FortuneContent.SYM_DOT;
                arrayOfString3[1] = FortuneContent.SYM_DOT;
                return arrayOfString3;
            case 16:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_SLASH);
                arrayOfString3[3] = FortuneContent.SYM_DOT;
                arrayOfString3[1] = FortuneContent.SYM_DOT;
                return arrayOfString3;
            case 13:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_SLASH);
                arrayOfString3[2] = FortuneContent.SYM_DOT;
                return arrayOfString3;
            case 44:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_DOT);
                arrayOfString3[4] = FortuneContent.SYM_SLASH;
                arrayOfString3[1] = FortuneContent.SYM_SLASH;
                return arrayOfString3;
            case 48:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_DOT);
                arrayOfString3[4] = FortuneContent.SYM_SLASH;
                return arrayOfString3;
            case 46:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_DOT);
                arrayOfString3[4] = FortuneContent.SYM_SLASH;
                arrayOfString3[2] = FortuneContent.SYM_SLASH;
                return arrayOfString3;
            case 45:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_DOT);
                arrayOfString3[4] = FortuneContent.SYM_SLASH;
                arrayOfString3[3] = FortuneContent.SYM_SLASH;
                arrayOfString3[2] = FortuneContent.SYM_SLASH;
                return arrayOfString3;
            case 85:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_DOT);
                arrayOfString3[3] = FortuneContent.SYM_SLASH;
                arrayOfString3[2] = FortuneContent.SYM_SLASH;
                return arrayOfString3;
            case 65:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_DOT);
                arrayOfString3[5] = FortuneContent.SYM_SLASH;
                arrayOfString3[3] = FortuneContent.SYM_SLASH;
                arrayOfString3[2] = FortuneContent.SYM_SLASH;
                return arrayOfString3;
            case 25:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_SLASH);
                arrayOfString3[6] = FortuneContent.SYM_DOT;
                arrayOfString3[1] = FortuneContent.SYM_DOT;
                return arrayOfString3;
            case 24:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_SLASH);
                arrayOfString3[6] = FortuneContent.SYM_DOT;
                arrayOfString3[3] = FortuneContent.SYM_DOT;
                arrayOfString3[2] = FortuneContent.SYM_DOT;
                return arrayOfString3;
            case 55:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_SLASH);
                arrayOfString3[4] = FortuneContent.SYM_DOT;
                arrayOfString3[1] = FortuneContent.SYM_DOT;
                return arrayOfString3;
            case 51:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_SLASH);
                arrayOfString3[4] = FortuneContent.SYM_DOT;
                return arrayOfString3;
            case 53:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_SLASH);
                arrayOfString3[4] = FortuneContent.SYM_DOT;
                arrayOfString3[2] = FortuneContent.SYM_DOT;
                return arrayOfString3;
            case 54:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_SLASH);
                arrayOfString3[4] = FortuneContent.SYM_DOT;
                arrayOfString3[3] = FortuneContent.SYM_DOT;
                arrayOfString3[2] = FortuneContent.SYM_DOT;
                return arrayOfString3;
            case 14:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_SLASH);
                arrayOfString3[3] = FortuneContent.SYM_DOT;
                arrayOfString3[2] = FortuneContent.SYM_DOT;
                return arrayOfString3;
            case 34:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_SLASH);
                arrayOfString3[5] = FortuneContent.SYM_DOT;
                arrayOfString3[3] = FortuneContent.SYM_DOT;
                arrayOfString3[2] = FortuneContent.SYM_DOT;
                return arrayOfString3;
            case 74:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_DOT);
                arrayOfString3[6] = FortuneContent.SYM_SLASH;
                arrayOfString3[1] = FortuneContent.SYM_SLASH;
                return arrayOfString3;
            case 75:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_DOT);
                arrayOfString3[6] = FortuneContent.SYM_SLASH;
                arrayOfString3[3] = FortuneContent.SYM_SLASH;
                arrayOfString3[2] = FortuneContent.SYM_SLASH;
                return arrayOfString3;
            case 66:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_DOT);
                arrayOfString3[5] = FortuneContent.SYM_SLASH;
                arrayOfString3[2] = FortuneContent.SYM_SLASH;
                return arrayOfString3;
            case 62:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_DOT);
                arrayOfString3[5] = FortuneContent.SYM_SLASH;
                arrayOfString3[2] = FortuneContent.SYM_SLASH;
                arrayOfString3[1] = FortuneContent.SYM_SLASH;
                return arrayOfString3;
            case 64:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_DOT);
                arrayOfString3[5] = FortuneContent.SYM_SLASH;
                arrayOfString3[1] = FortuneContent.SYM_SLASH;
                return arrayOfString3;
            case 63:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_DOT);
                arrayOfString3[5] = FortuneContent.SYM_SLASH;
                arrayOfString3[3] = FortuneContent.SYM_SLASH;
                arrayOfString3[1] = FortuneContent.SYM_SLASH;
                return arrayOfString3;
            case 23:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_SLASH);
                arrayOfString3[6] = FortuneContent.SYM_DOT;
                arrayOfString3[2] = FortuneContent.SYM_DOT;
                return arrayOfString3;
            case 43:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_SLASH);
                arrayOfString3[6] = FortuneContent.SYM_DOT;
                arrayOfString3[5] = FortuneContent.SYM_DOT;
                arrayOfString3[2] = FortuneContent.SYM_DOT;
                return arrayOfString3;
            case 83:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_DOT);
                arrayOfString3[3] = FortuneContent.SYM_SLASH;
                arrayOfString3[1] = FortuneContent.SYM_SLASH;
                return arrayOfString3;
            case 86:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_DOT);
                arrayOfString3[2] = FortuneContent.SYM_SLASH;
                return arrayOfString3;
            case 77:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_DOT);
                arrayOfString3[6] = FortuneContent.SYM_SLASH;
                arrayOfString3[3] = FortuneContent.SYM_SLASH;
                return arrayOfString3;
            case 73:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_DOT);
                arrayOfString3[6] = FortuneContent.SYM_SLASH;
                arrayOfString3[3] = FortuneContent.SYM_SLASH;
                arrayOfString3[1] = FortuneContent.SYM_SLASH;
                return arrayOfString3;
            case 71:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_SLASH);
                arrayOfString3[5] = FortuneContent.SYM_DOT;
                arrayOfString3[4] = FortuneContent.SYM_DOT;
                return arrayOfString3;
            case 72:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_SLASH);
                arrayOfString3[5] = FortuneContent.SYM_DOT;
                arrayOfString3[4] = FortuneContent.SYM_DOT;
                arrayOfString3[3] = FortuneContent.SYM_DOT;
                return arrayOfString3;
            case 32:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_SLASH);
                arrayOfString3[5] = FortuneContent.SYM_DOT;
                arrayOfString3[3] = FortuneContent.SYM_DOT;
                return arrayOfString3;
            case 12:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_SLASH);
                arrayOfString3[3] = FortuneContent.SYM_DOT;
                return arrayOfString3;
            case 52:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_SLASH);
                arrayOfString3[4] = FortuneContent.SYM_DOT;
                arrayOfString3[3] = FortuneContent.SYM_DOT;
                return arrayOfString3;
            case 57:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_DOT);
                arrayOfString3[6] = FortuneContent.SYM_SLASH;
                arrayOfString3[5] = FortuneContent.SYM_SLASH;
                arrayOfString3[3] = FortuneContent.SYM_SLASH;
                return arrayOfString3;
            case 88:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_DOT);
                return arrayOfString3;
            case 84:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_DOT);
                arrayOfString3[1] = FortuneContent.SYM_SLASH;
                return arrayOfString3;
            case 82:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_DOT);
                arrayOfString3[2] = FortuneContent.SYM_SLASH;
                arrayOfString3[1] = FortuneContent.SYM_SLASH;
                return arrayOfString3;
            case 81:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_DOT);
                arrayOfString3[3] = FortuneContent.SYM_SLASH;
                arrayOfString3[2] = FortuneContent.SYM_SLASH;
                arrayOfString3[1] = FortuneContent.SYM_SLASH;
                return arrayOfString3;
            case 41:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_SLASH);
                arrayOfString3[6] = FortuneContent.SYM_DOT;
                arrayOfString3[5] = FortuneContent.SYM_DOT;
                return arrayOfString3;
            case 21:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_SLASH);
                arrayOfString3[6] = FortuneContent.SYM_DOT;
                return arrayOfString3;
            case 61:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_SLASH);
                arrayOfString3[6] = FortuneContent.SYM_DOT;
                arrayOfString3[4] = FortuneContent.SYM_DOT;
                return arrayOfString3;
            case 68:
                arrayOfString3 = Utils.getValuedArray(arrayOfString3, FortuneContent.SYM_DOT);
                arrayOfString3[5] = FortuneContent.SYM_SLASH;
                return arrayOfString3;
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
                return arrayOfString3;
        }
    }
}
