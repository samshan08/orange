package org.sam.fortuneteller.data;

/**
 * Created by SAM on 2018/3/31.
 */
public interface FortuneContent {

    String[] TIMES = { "第一次", "第二次", "第三次", "第四次", "第五次", "第六次" };
    String CODE_TEXT = "摇得：%s卦；点击“确定”排盘。";
    String TIME_TEXT = "时间: %s年%s月%s日%s时%s分";
    String NONG_TIME_TEXT = "%s年%s月%s日(%s)%s时   %d卦";
    String[] RESULT_EACH = { "老阴Ｘ", "少阳／", "少阴//", "老阳Ｏ" };
    String[] TOP = { "甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸" };
    String[] DOWN = { "子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥" };

    String[] FORTUNE_PARTS = { "", "子寅辰午申戌", "巳卯丑亥酉未", "卯丑亥酉未巳", "子寅辰午申戌", "丑亥酉未巳卯", "寅辰午申戌子", "辰午申戌子寅", "未巳卯丑亥酉" };
}
