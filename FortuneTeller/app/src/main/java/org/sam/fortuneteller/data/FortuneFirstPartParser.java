package org.sam.fortuneteller.data;

/**
 * Created by SAM on 2018/4/14.
 */
public class FortuneFirstPartParser {


    public static class FirstPart {

        private final String header;

        private final String[] arrays;

        public FirstPart(String header, String[] arrays) {
            this.header = header;
            this.arrays = arrays;
        }

        public String getHeader() {
            return header;
        }

        public String[] getArrays() {
            return arrays;
        }
    }

    public static FirstPart getFirstFortunePart(String paramString) {
        String[] arrayOfString5 = new String[7];
        String paramString1 = "";
        if (paramString.equals("")) {
            return new FirstPart(paramString1, Utils.getEmptyArray(arrayOfString5));
        }
        arrayOfString5[0] = "";
        switch (Integer.parseInt(paramString)) {
            case 11: {
                arrayOfString5[6] = "　　父";
                arrayOfString5[5] = "　　兄";
                arrayOfString5[4] = "　　官";
                arrayOfString5[3] = "　　父";
                arrayOfString5[2] = "　　财";
                arrayOfString5[1] = "　　孙";
                paramString1 = "乾";
                break;
            }
            case 15: {
                arrayOfString5[6] = "　　父";
                arrayOfString5[5] = "　　兄";
                arrayOfString5[4] = "　　官";
                arrayOfString5[3] = "　　兄";
                arrayOfString5[2] = "财寅孙";
                arrayOfString5[1] = "　　父";
                paramString1 = "乾";
                break;
            }
            case 17: {
                arrayOfString5[6] = "　　父";
                arrayOfString5[5] = "　　兄";
                arrayOfString5[4] = "　　官";
                arrayOfString5[3] = "　　兄";
                arrayOfString5[2] = "财寅官";
                arrayOfString5[1] = "孙子父";
                paramString1 = "乾";
                break;
            }
            case 18: {
                arrayOfString5[6] = "　　父";
                arrayOfString5[5] = "　　兄";
                arrayOfString5[4] = "　　官";
                arrayOfString5[3] = "　　财";
                arrayOfString5[2] = "　　官";
                arrayOfString5[1] = "孙子父";
                paramString1 = "乾";
                break;
            }
            case 58: {
                arrayOfString5[6] = "　　财";
                arrayOfString5[5] = "兄申官";
                arrayOfString5[4] = "　　父";
                arrayOfString5[3] = "　　财";
                arrayOfString5[2] = "　　官";
                arrayOfString5[1] = "孙子父";
                paramString1 = "乾";
                break;
            }
            case 78: {
                arrayOfString5[6] = "　　财";
                arrayOfString5[5] = "兄申孙";
                arrayOfString5[4] = "　　父";
                arrayOfString5[3] = "　　财";
                arrayOfString5[2] = "　　官";
                arrayOfString5[1] = "　　父";
                paramString1 = "乾";
                break;
            }
            case 38: {
                arrayOfString5[6] = "　　官";
                arrayOfString5[5] = "　　父";
                arrayOfString5[4] = "　　兄";
                arrayOfString5[3] = "　　财";
                arrayOfString5[2] = "　　官";
                arrayOfString5[1] = "孙子父";
                paramString1 = "乾";
                break;
            }
            case 31: {
                arrayOfString5[6] = "　　官";
                arrayOfString5[5] = "　　父";
                arrayOfString5[4] = "　　兄";
                arrayOfString5[3] = "　　父";
                arrayOfString5[2] = "　　财";
                arrayOfString5[1] = "　　孙";
                paramString1 = "乾";
                break;
            }
            case 22: {
                arrayOfString5[6] = "　　父";
                arrayOfString5[5] = "　　兄";
                arrayOfString5[4] = "　　孙";
                arrayOfString5[3] = "　　父";
                arrayOfString5[2] = "　　财";
                arrayOfString5[1] = "　　官";
                paramString1 = "兑";
                break;
            }
            case 26: {
                arrayOfString5[6] = "　　父";
                arrayOfString5[5] = "　　兄";
                arrayOfString5[4] = "　　孙";
                arrayOfString5[3] = "　　官";
                arrayOfString5[2] = "　　父";
                arrayOfString5[1] = "　　财";
                paramString1 = "兑";
                break;
            }
            case 28: {
                arrayOfString5[6] = "　　父";
                arrayOfString5[5] = "　　兄";
                arrayOfString5[4] = "　　孙";
                arrayOfString5[3] = "　　财";
                arrayOfString5[2] = "　　官";
                arrayOfString5[1] = "　　父";
                paramString1 = "兑";
                break;
            }
            case 27: {
                arrayOfString5[6] = "　　父";
                arrayOfString5[5] = "　　兄";
                arrayOfString5[4] = "　　孙";
                arrayOfString5[3] = "　　兄";
                arrayOfString5[2] = "财卯官";
                arrayOfString5[1] = "　　父";
                paramString1 = "兑";
                break;
            }
            case 67: {
                arrayOfString5[6] = "　　孙";
                arrayOfString5[5] = "　　父";
                arrayOfString5[4] = "　　兄";
                arrayOfString5[3] = "　　兄";
                arrayOfString5[2] = "财卯官";
                arrayOfString5[1] = "　　父";
                paramString1 = "兑";
                break;
            }
            case 87: {
                arrayOfString5[6] = "　　兄";
                arrayOfString5[5] = "　　孙";
                arrayOfString5[4] = "　　父";
                arrayOfString5[3] = "　　兄";
                arrayOfString5[2] = "财卯官";
                arrayOfString5[1] = "　　父";
                paramString1 = "兑";
                break;
            }
            case 47: {
                arrayOfString5[6] = "　　父";
                arrayOfString5[5] = "　　兄";
                arrayOfString5[4] = "孙亥官";
                arrayOfString5[3] = "　　兄";
                arrayOfString5[2] = "财卯官";
                arrayOfString5[1] = "　　父";
                paramString1 = "兑";
                break;
            }
            case 42: {
                arrayOfString5[6] = "　　父";
                arrayOfString5[5] = "　　兄";
                arrayOfString5[4] = "孙亥官";
                arrayOfString5[3] = "　　父";
                arrayOfString5[2] = "　　财";
                arrayOfString5[1] = "　　官";
                paramString1 = "兑";
                break;
            }
            case 33: {
                arrayOfString5[6] = "　　兄";
                arrayOfString5[5] = "　　孙";
                arrayOfString5[4] = "　　财";
                arrayOfString5[3] = "　　官";
                arrayOfString5[2] = "　　孙";
                arrayOfString5[1] = "　　父";
                paramString1 = "离";
                break;
            }
            case 37: {
                arrayOfString5[6] = "　　兄";
                arrayOfString5[5] = "　　孙";
                arrayOfString5[4] = "　　财";
                arrayOfString5[3] = "官亥财";
                arrayOfString5[2] = "　　兄";
                arrayOfString5[1] = "父卯孙";
                paramString1 = "离";
                break;
            }
            case 35: {
                arrayOfString5[6] = "　　兄";
                arrayOfString5[5] = "　　孙";
                arrayOfString5[4] = "　　财";
                arrayOfString5[3] = "　　财";
                arrayOfString5[2] = "　　官";
                arrayOfString5[1] = "父卯孙";
                paramString1 = "离";
                break;
            }
            case 36: {
                arrayOfString5[6] = "　　兄";
                arrayOfString5[5] = "　　孙";
                arrayOfString5[4] = "　　财";
                arrayOfString5[3] = "官亥兄";
                arrayOfString5[2] = "　　孙";
                arrayOfString5[1] = "　　父";
                paramString1 = "离";
                break;
            }
            case 76: {
                arrayOfString5[6] = "　　父";
                arrayOfString5[5] = "　　官";
                arrayOfString5[4] = "财酉孙";
                arrayOfString5[3] = "　　兄";
                arrayOfString5[2] = "　　孙";
                arrayOfString5[1] = "　　父";
                paramString1 = "离";
                break;
            }
            case 56: {
                arrayOfString5[6] = "　　父";
                arrayOfString5[5] = "　　兄";
                arrayOfString5[4] = "财酉孙";
                arrayOfString5[3] = "官亥兄";
                arrayOfString5[2] = "　　孙";
                arrayOfString5[1] = "　　父";
                paramString1 = "离";
                break;
            }
            case 16: {
                arrayOfString5[6] = "　　孙";
                arrayOfString5[5] = "　　财";
                arrayOfString5[4] = "　　兄";
                arrayOfString5[3] = "官亥兄";
                arrayOfString5[2] = "　　孙";
                arrayOfString5[1] = "　　父";
                paramString1 = "离";
                break;
            }
            case 13: {
                arrayOfString5[6] = "　　孙";
                arrayOfString5[5] = "　　财";
                arrayOfString5[4] = "　　兄";
                arrayOfString5[3] = "　　官";
                arrayOfString5[2] = "　　孙";
                arrayOfString5[1] = "　　父";
                paramString1 = "离";
                break;
            }
            case 44: {
                arrayOfString5[6] = "　　财";
                arrayOfString5[5] = "　　官";
                arrayOfString5[4] = "　　孙";
                arrayOfString5[3] = "　　财";
                arrayOfString5[2] = "　　兄";
                arrayOfString5[1] = "　　父";
                paramString1 = "震";
                break;
            }
            case 48: {
                arrayOfString5[6] = "　　财";
                arrayOfString5[5] = "　　官";
                arrayOfString5[4] = "　　孙";
                arrayOfString5[3] = "　　兄";
                arrayOfString5[2] = "　　孙";
                arrayOfString5[1] = "父子财";
                paramString1 = "震";
                break;
            }
            case 46: {
                arrayOfString5[6] = "　　财";
                arrayOfString5[5] = "　　官";
                arrayOfString5[4] = "　　孙";
                arrayOfString5[3] = "　　孙";
                arrayOfString5[2] = "　　财";
                arrayOfString5[1] = "父子兄";
                paramString1 = "震";
                break;
            }
            case 45: {
                arrayOfString5[6] = "　　财";
                arrayOfString5[5] = "　　官";
                arrayOfString5[4] = "　　孙";
                arrayOfString5[3] = "　　官";
                arrayOfString5[2] = "兄寅父";
                arrayOfString5[1] = "　　财";
                paramString1 = "震";
                break;
            }
            case 85: {
                arrayOfString5[6] = "　　官";
                arrayOfString5[5] = "　　父";
                arrayOfString5[4] = "孙午财";
                arrayOfString5[3] = "　　官";
                arrayOfString5[2] = "兄寅父";
                arrayOfString5[1] = "    财";
                paramString1 = "震";
                break;
            }
            case 65: {
                arrayOfString5[6] = "　　父";
                arrayOfString5[5] = "　　财";
                arrayOfString5[4] = "孙午官";
                arrayOfString5[3] = "　　官";
                arrayOfString5[2] = "兄寅父";
                arrayOfString5[1] = "　　财";
                paramString1 = "震";
                break;
            }
            case 25: {
                arrayOfString5[6] = "　　财";
                arrayOfString5[5] = "　　官";
                arrayOfString5[4] = "孙午父";
                arrayOfString5[3] = "　　官";
                arrayOfString5[2] = "兄寅父";
                arrayOfString5[1] = "　　财";
                paramString1 = "震";
                break;
            }
            case 24: {
                arrayOfString5[6] = "　　财";
                arrayOfString5[5] = "　　官";
                arrayOfString5[4] = "孙午父";
                arrayOfString5[3] = "　　财";
                arrayOfString5[2] = "　　兄";
                arrayOfString5[1] = "　　父";
                paramString1 = "震";
                break;
            }
            case 55: {
                arrayOfString5[6] = "　　兄";
                arrayOfString5[5] = "　　孙";
                arrayOfString5[4] = "　　财";
                arrayOfString5[3] = "　　官";
                arrayOfString5[2] = "　　父";
                arrayOfString5[1] = "　　财";
                paramString1 = "巽";
                break;
            }
            case 51: {
                arrayOfString5[6] = "　　兄";
                arrayOfString5[5] = "　　孙";
                arrayOfString5[4] = "　　财";
                arrayOfString5[3] = "官酉财";
                arrayOfString5[2] = "　　兄";
                arrayOfString5[1] = "　　父";
                paramString1 = "巽";
                break;
            }
            case 53: {
                arrayOfString5[6] = "　　兄";
                arrayOfString5[5] = "　　孙";
                arrayOfString5[4] = "　　财";
                arrayOfString5[3] = "官酉父";
                arrayOfString5[2] = "　　财";
                arrayOfString5[1] = "　　兄";
                paramString1 = "巽";
                break;
            }
            case 54: {
                arrayOfString5[6] = "　　兄";
                arrayOfString5[5] = "　　孙";
                arrayOfString5[4] = "　　财";
                arrayOfString5[3] = "官酉财";
                arrayOfString5[2] = "　　兄";
                arrayOfString5[1] = "　　父";
                paramString1 = "巽";
                break;
            }
            case 14: {
                arrayOfString5[6] = "　　财";
                arrayOfString5[5] = "　　官";
                arrayOfString5[4] = "　　孙";
                arrayOfString5[3] = "　　财";
                arrayOfString5[2] = "　　兄";
                arrayOfString5[1] = "　　父";
                paramString1 = "巽";
                break;
            }
            case 34: {
                arrayOfString5[6] = "　　孙";
                arrayOfString5[5] = "　　财";
                arrayOfString5[4] = "　　官";
                arrayOfString5[3] = "　　财";
                arrayOfString5[2] = "　　兄";
                arrayOfString5[1] = "　　父";
                paramString1 = "巽";
                break;
            }
            case 74: {
                arrayOfString5[6] = "　　兄";
                arrayOfString5[5] = "孙巳父";
                arrayOfString5[4] = "　　财";
                arrayOfString5[3] = "官酉财";
                arrayOfString5[2] = "　　兄";
                arrayOfString5[1] = "　　父";
                paramString1 = "巽";
                break;
            }
            case 75: {
                arrayOfString5[6] = "　　兄";
                arrayOfString5[5] = "孙巳父";
                arrayOfString5[4] = "　　财";
                arrayOfString5[3] = "　　官";
                arrayOfString5[2] = "　　父";
                arrayOfString5[1] = "　　财";
                paramString1 = "巽";
                break;
            }
            case 66: {
                arrayOfString5[6] = "　　兄";
                arrayOfString5[5] = "　　官";
                arrayOfString5[4] = "　　父";
                arrayOfString5[3] = "　　财";
                arrayOfString5[2] = "　　官";
                arrayOfString5[1] = "　　孙";
                paramString1 = "坎";
                break;
            }
            case 62: {
                arrayOfString5[6] = "　　兄";
                arrayOfString5[5] = "　　官";
                arrayOfString5[4] = "　　父";
                arrayOfString5[3] = "　　官";
                arrayOfString5[2] = "　　孙";
                arrayOfString5[1] = "　　财";
                paramString1 = "坎";
                break;
            }
            case 64: {
                arrayOfString5[6] = "　　兄";
                arrayOfString5[5] = "　　官";
                arrayOfString5[4] = "　　父";
                arrayOfString5[3] = "财午官";
                arrayOfString5[2] = "　　孙";
                arrayOfString5[1] = "　　兄";
                paramString1 = "坎";
                break;
            }
            case 63: {
                arrayOfString5[6] = "　　兄";
                arrayOfString5[5] = "　　官";
                arrayOfString5[4] = "　　父";
                arrayOfString5[3] = "财午兄";
                arrayOfString5[2] = "　　官";
                arrayOfString5[1] = "　　孙";
                paramString1 = "坎";
                break;
            }
            case 23: {
                arrayOfString5[6] = "　　官";
                arrayOfString5[5] = "　　父";
                arrayOfString5[4] = "　　兄";
                arrayOfString5[3] = "财午兄";
                arrayOfString5[2] = "　　官";
                arrayOfString5[1] = "　　孙";
                paramString1 = "坎";
                break;
            }
            case 43: {
                arrayOfString5[6] = "　　官";
                arrayOfString5[5] = "　　父";
                arrayOfString5[4] = "　　财";
                arrayOfString5[3] = "　　兄";
                arrayOfString5[2] = "　　官";
                arrayOfString5[1] = "　　孙";
                paramString1 = "坎";
            }
            break;
            case 83: {
                arrayOfString5[6] = "　　父";
                arrayOfString5[5] = "　　兄";
                arrayOfString5[4] = "　　官";
                arrayOfString5[3] = "财午兄";
                arrayOfString5[2] = "　　官";
                arrayOfString5[1] = "　　孙";
                paramString1 = "坎";
                break;
            }
            case 86: {
                arrayOfString5[6] = "　　父";
                arrayOfString5[5] = "　　兄";
                arrayOfString5[4] = "　　官";
                arrayOfString5[3] = "　　财";
                arrayOfString5[2] = "　　官";
                arrayOfString5[1] = "　　孙";
                paramString1 = "坎";
                break;
            }
            case 77: {
                arrayOfString5[6] = "　　官";
                arrayOfString5[5] = "　　财";
                arrayOfString5[4] = "　　兄";
                arrayOfString5[3] = "　　孙";
                arrayOfString5[2] = "　　父";
                arrayOfString5[1] = "　　兄";
                paramString1 = "艮";
                break;
            }
            case 73: {
                arrayOfString5[6] = "　　官";
                arrayOfString5[5] = "　　财";
                arrayOfString5[4] = "　　兄";
                arrayOfString5[3] = "孙申财";
                arrayOfString5[2] = "父午兄";
                arrayOfString5[1] = "　　官";
                paramString1 = "艮";
                break;
            }
            case 71: {
                arrayOfString5[6] = "　　官";
                arrayOfString5[5] = "　　财";
                arrayOfString5[4] = "　　兄";
                arrayOfString5[3] = "孙申兄";
                arrayOfString5[2] = "父午官";
                arrayOfString5[1] = "　　财";
                paramString1 = "艮";
                break;
            }
            case 72: {
                arrayOfString5[6] = "　　官";
                arrayOfString5[5] = "　　财";
                arrayOfString5[4] = "　　兄";
                arrayOfString5[3] = "孙申兄";
                arrayOfString5[2] = "　　官";
                arrayOfString5[1] = "　　父";
                paramString1 = "艮";
                break;
            }
            case 32: {
                arrayOfString5[6] = "　　父";
                arrayOfString5[5] = "财子兄";
                arrayOfString5[4] = "　　孙";
                arrayOfString5[3] = "　　兄";
                arrayOfString5[2] = "　　官";
                arrayOfString5[1] = "　　父";
                paramString1 = "艮";
                break;
            }
            case 12: {
                arrayOfString5[6] = "　　兄";
                arrayOfString5[5] = "财子孙";
                arrayOfString5[4] = "　　父";
                arrayOfString5[3] = "　　兄";
                arrayOfString5[2] = "　　官";
                arrayOfString5[1] = "　　父";
                paramString1 = "艮";
                break;
            }
            case 52: {
                arrayOfString5[6] = "　　官";
                arrayOfString5[5] = "财子父";
                arrayOfString5[4] = "　　兄";
                arrayOfString5[3] = "孙申兄";
                arrayOfString5[2] = "　　官";
                arrayOfString5[1] = "　　父";
                paramString1 = "艮";
                break;
            }
            case 57: {
                arrayOfString5[6] = "　　官";
                arrayOfString5[5] = "财子父";
                arrayOfString5[4] = "　　兄";
                arrayOfString5[3] = "　　孙";
                arrayOfString5[2] = "　　父";
                arrayOfString5[1] = "　　兄";
                paramString1 = "艮";
                break;
            }
            case 88: {
                arrayOfString5[6] = "　　孙";
                arrayOfString5[5] = "　　财";
                arrayOfString5[4] = "　　兄";
                arrayOfString5[3] = "　　官";
                arrayOfString5[2] = "　　父";
                arrayOfString5[1] = "　　兄";
                paramString1 = "坤";
                break;
            }
            case 81: {
                arrayOfString5[6] = "　　孙";
                arrayOfString5[5] = "　　财";
                arrayOfString5[4] = "　　兄";
                arrayOfString5[3] = "　　兄";
                arrayOfString5[2] = "父巳官";
                arrayOfString5[1] = "　　财";
                paramString1 = "坤";
                break;
            }
            case 82: {
                arrayOfString5[6] = "　　孙";
                arrayOfString5[5] = "　　财";
                arrayOfString5[4] = "　　兄";
                arrayOfString5[3] = "　　兄";
                arrayOfString5[2] = "　　官";
                arrayOfString5[1] = "　　父";
                paramString1 = "坤";
                break;
            }
            case 84: {
                arrayOfString5[6] = "　　孙";
                arrayOfString5[5] = "　　财";
                arrayOfString5[4] = "　　兄";
                arrayOfString5[3] = "　　兄";
                arrayOfString5[2] = "父巳官";
                arrayOfString5[1] = "　　财";
                paramString1 = "坤";
                break;
            }
            case 41: {
                arrayOfString5[6] = "　　兄";
                arrayOfString5[5] = "　　孙";
                arrayOfString5[4] = "　　父";
                arrayOfString5[3] = "　　兄";
                arrayOfString5[2] = "　　官";
                arrayOfString5[1] = "　　财";
                paramString1 = "坤";
                break;
            }
            case 21: {
                arrayOfString5[6] = "　　兄";
                arrayOfString5[5] = "　　孙";
                arrayOfString5[4] = "　　财";
                arrayOfString5[3] = "　　兄";
                arrayOfString5[2] = "父巳官";
                arrayOfString5[1] = "　　财";
                paramString1 = "坤";
                break;
            }
            case 61: {
                arrayOfString5[6] = "　　财";
                arrayOfString5[5] = "　　兄";
                arrayOfString5[4] = "　　孙";
                arrayOfString5[3] = "　　兄";
                arrayOfString5[2] = "父巳官";
                arrayOfString5[1] = "　　财";
                paramString1 = "坤";
                break;
            }
            case 68: {
                arrayOfString5[6] = "　　财";
                arrayOfString5[5] = "　　兄";
                arrayOfString5[4] = "　　孙";
                arrayOfString5[3] = "　　官";
                arrayOfString5[2] = "　　父";
                arrayOfString5[1] = "　　兄";
                paramString1 = "坤";
                break;
            }
            default: {
                break;
            }
        }
        return new FirstPart(paramString1, arrayOfString5);
    }

}
