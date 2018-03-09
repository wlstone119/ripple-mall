package com.stone.ripple.util.tool;

/**
 * double的大数值时不显示成科学计数法，转换一下成字符串 比如：5E8 转换换成 50000000
 *
 * @param doubles
 * @return
 */
import java.math.BigDecimal;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;

/**
 * bogda可以使用这个StringUtils。该类继承了apache-common包的字符工具类。
 *
 */
public class StringUtil extends StringUtils {

    // 一个空的字符串。
    public static String EMPTY_STRING = "";

    /**
     * 判断一个传入的字符串是否包含英文字母。 ps: 传入null认为不包含英文字母。
     *
     * @param words 传入的源字符串。
     * @return
     */
    public static boolean containEnglish(String word) {
        if (word == null) {
            return false;
        }

        for (int i = 0; i < word.length(); i++) {
            if (Character.isLetter(word.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 如果传入的值是null，返回空字符串，如果不是null，返回本身。
     *
     * @param word 传入的源字符串。
     * @return
     */
    public static String getNotNullValue(String word) {
        return word == null ? "" : word;
    }

    /**
     * 如果传入的值是null，返回空字符串，如果不是null，返回本身。
     *
     * @param word 传入的源字符串。
     * @return
     */
    public static String getNotNullValue(String word, String defaultWord) {
        return word == null ? defaultWord : word;
    }

    /**
     * 判断一个传入的字符串是否全是英文 ps: 传入null返回false。
     *
     * @param words 传入的源字符串。
     * @return
     */
    public static boolean isEnglishWord(String word) {
        if (word == null) {
            return false;
        }

        for (int i = 0; i < word.length(); i++) {
            if (!Character.isLetter(word.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断一个传入的字符串是数字 ps: 传入null返回false。
     *
     * @param words 传入的源字符串。
     * @return
     */
    public static boolean isNumber(String word) {
        if (isBlank(word)) {
            return false;
        }

        for (int i = 0; i < word.length(); i++) {
            if (!Character.isDigit(word.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断一个传入的字符串是数字和字母的混合 ps: 传入null返回false。
     *
     * @param words 传入的源字符串。
     * @return
     */
    public static boolean isNumberAndLetter(String word) {
        if (word == null) {
            return false;
        }

        // String a = "asgdc";
        // System.out.println(a.matches("^[a-zA-Z]*"));

        for (int i = 0; i < word.length(); i++) {
            boolean isLetter = (word.charAt(i) >= 'A' && word.charAt(i) <= 'Z')
                               || (word.charAt(i) >= 'a' && word.charAt(i) <= 'z');
            boolean isNumber = word.charAt(i) >= '0' && word.charAt(i) <= '9';
            if (!(isLetter || isNumber)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 将_分开的字符串合并，并且首字母大写。如pure_benefit_contract，返回pureBenefitContract。
     *
     * @param str
     * @return
     */
    public static String getMergeString(String str) {
        if (StringUtil.isBlank(str)) {
            return "";
        }

        if (str.indexOf("_") == -1) {
            return str;
        }

        String[] pages = str.split("_");
        StringBuilder sb = new StringBuilder(pages[0]);

        for (int i = 1; i < pages.length; i++) {
            String p = pages[i];
            sb.append(convertFirstLetterToUpper(p));
        }
        return sb.toString();
    }

    /**
     * 将字符串首字母大写
     */
    public static String convertFirstLetterToUpper(String str) {
        if (StringUtil.isBlank(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();

        sb.append(str.substring(0, 1).toUpperCase()).append(str.substring(1));

        return sb.toString();
    }

    /**
     * 对double四舍五入，小数精度通过precision指定
     *
     * @param number
     * @param precision
     * @return
     */
    public static double round(Double number, int precision) {
        if (number == null) {
            return 0;
        }
        if (precision < 0) {
            precision = 2;
        }
        BigDecimal decimal = new BigDecimal(Double.toString(number)).setScale(precision, BigDecimal.ROUND_HALF_UP);
        return decimal.doubleValue();
    }

    /**
     * 对double四舍五入，保留2位小数
     *
     * @param number
     * @return
     */
    public static double round(Double number) {
        return round(number, 2);
    }

    /**
     * 格式化浮点型数字，返回字符串为带千分位的会计数字，并保留默认两位小数
     *
     * @param obj 浮点型数字
     * @return
     */
    public static String formatNumberToCurrency(Object obj) {
        return formatNumberToCurrency(obj, 2);
    }

    /**
     * 格式化浮点型数字，返回字符串为带千分位的会计数字，并保留指定精度的小数
     *
     * @param obj 浮点型数字
     * @param precision 小数精度
     * @return
     */
    public static String formatNumberToCurrency(Object obj, int precision) {
        if (obj == null) return "";

        if (obj instanceof Double || obj instanceof Float) {
            return String.format("%,." + precision + "f", obj);
        }

        Double d = null;
        if (obj instanceof String) {
            d = Double.valueOf((String) obj);
        } else if (obj instanceof Integer) {
            d = ((Integer) obj).doubleValue();
        } else if (obj instanceof Long) {
            d = ((Long) obj).doubleValue();
        } else if (obj instanceof BigDecimal) {
            d = ((BigDecimal) obj).doubleValue();
        } else {
            return "";
        }
        if (precision < 0) {
            precision = 2;
        }

        return String.format("%,." + precision + "f", d);
    }

    /**
     * * 格式化浮点型数字，返回字符串不含千分位，并保留指定精度的小数
     * <p>
     * 3.156返回3.16<br>
     * 3.154返回3.15<br>
     * 3.0返回3.00
     * </p>
     *
     * @param obj 浮点型数字
     * @param precision 小数精度
     * @return
     */
    public static String formatNumber(Object obj, int precision) {
        if (obj instanceof Double || obj instanceof Float) {
            return String.format("%." + precision + "f", obj);
        }

        Double d = null;
        if (obj instanceof String) {
            d = Double.valueOf((String) obj);
        } else if (obj instanceof Integer) {
            d = ((Integer) obj).doubleValue();
        } else if (obj instanceof Long) {
            d = ((Long) obj).doubleValue();
        } else if (obj instanceof BigDecimal) {
            d = ((BigDecimal) obj).doubleValue();
        } else {
            return "";
        }
        if (precision < 0) {
            precision = 2;
        }

        return String.format("%." + precision + "f", d);
    }

    /**
     * 格式化浮点型数字，返回字符串不含千分位，并保留2位小数
     */
    public static String formatNumber(Object obj) {
        return formatNumber(obj, 2);
    }

    public static final boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static final boolean isEmpty(String str) {
        return (null == str || str.length() == 0);
    }

    /**
     * 遗留方法
     *
     * @param doubles
     * @return
     */
    public static String formatDouble(Double doubles) {
        return formatNumber(doubles);
    }

    /**
     * 将带千分位的数字字符串，去掉千分位并返回
     *
     * @param currencyNumber
     * @return
     */
    public static String formatCurrencyToNumber(String currencyNumber) {

        if (StringUtil.isBlank(currencyNumber)) {
            return "";
        }

        return currencyNumber.replaceAll(",", "");

    }

    /**
     * 截取字符串并显示 ，
     *
     * @param str 待截取 字符串
     * @param size 截取长度
     * @param endStr 截取后加的结束字符串
     * @return
     */
    public static String subAndShow(String str, int size, String endStr) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        boolean mustSub = str.length() > size;
        size = str.length() > size ? size : str.length();
        if (mustSub) {
            return str.substring(0, size) + endStr;
        } else {
            return str;
        }
    }

    /**
     * 截取固定长度字符串并显示成 { 截取的字符串 }。。。
     *
     * @param str 待截取 字符串
     * @param size 截取长度
     * @param endStr 截取后加的结束字符串
     * @return
     */
    public static String subAndShow(String str, int size) {
        return subAndShow(str, size, "...");
    }

    /**
     * 验证字符串长度（小于等于endx）
     *
     * @param isNull 是否必填，true:必填，false:非
     * @param str
     * @param endx
     * @return
     */
    public static boolean stringLengthValidate(String str, boolean isNull, int endx) {
        if (!isNull && isBlank(str)) {
            // 可以为空且为空的时候直接返回true；
            return true;
        } else {
            // 不可以为空时，长度小于等于ends时返回true;
            if (isBlank(str)) return false;
            return str.trim().length() <= endx;
        }
    }

    /**
     * 判断多个复选框是否被选中
     *
     * @param key
     * @param keys
     * @return
     */
    public static boolean isChecked(String key, String keys) {
        if (isBlank(keys) || isBlank(key)) {
            return false;
        } else if (keys.indexOf(",") > -1) {
            String[] keyArray = keys.split(",");
            for (String str : keyArray) {
                if (equals(key, str)) {
                    return true;
                }
            }
        } else if (StringUtil.equals(key, keys)) {
            return true;
        }
        return false;
    }

    /**
     * 返回两个数值的和
     *
     * @param num1
     * @param num2
     * @return
     */
    public static int sumTwoNum(int num1, int num2) {
        return num1 + num2;
    }

    private static String[] zh   = new String[] { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };

    private static String[] unit = new String[] { "", "十", "百", "千", "万", "十", "百", "千", "亿", "十" };

    /**
     * 数字装换为标准数字
     *
     * @param number
     * @return
     */
    public static String convertNumToChin(int number) {
        Assert.isTrue(number > 0, "目前暂不支持负数");
        String str = "";
        StringBuffer sb = new StringBuffer(String.valueOf(number));
        sb = sb.reverse();
        int r = 0;
        int l = 0;
        for (int j = 0; j < sb.length(); j++) {
            /**
             * 当前数字
             */
            r = Integer.valueOf(sb.substring(j, j + 1));

            if (j != 0)
            /**
             * 上一个数字
             */
            l = Integer.valueOf(sb.substring(j - 1, j));

            if (j == 0) {
                if (r != 0 || sb.length() == 1) str = zh[r];
                continue;
            }
            if (j == 1 || j == 2 || j == 3 || j == 5 || j == 6 || j == 7 || j == 9) {
                if (r != 0) str = zh[r] + unit[j] + str;
                else if (l != 0) str = zh[r] + str;
                continue;
            }
            if (j == 4 || j == 8) {
                str = unit[j] + str;
                if ((l != 0 && r == 0) || r != 0) str = zh[r] + str;
                continue;
            }
        }
        if (9 < number && number < 20) {
            str = str.substring(1);
        }
        return str;
    }

    /**
     * sources是否包含target
     *
     * @param target
     * @param sources
     * @return
     */
    @SafeVarargs
    public static <T> boolean contain(T target, T... sources) {
        if (target == null && sources != null) {
            return false;
        }
        for (T source : sources) {
            if (Objects.equals(target, source)) {
                return true;
            }
        }
        return false;
    }

    public static String handleAreaCode(String areaCode) {
        if (StringUtil.isNotBlank(areaCode)) {
            if (areaCode.endsWith("0000")) {
                return substring(areaCode, 0, 2);
            } else if (areaCode.endsWith("00")) {
                return substring(areaCode, 0, 4);
            } else {
                return areaCode;
            }
        }
        return areaCode;
    }

    public static String stringify(Object... elements) {
        StringBuilder strbuilder = new StringBuilder();
        for (Object obj : elements) {
            strbuilder.append("{").append(JSON.toJSONString(obj)).append("}, ");
        }
        return strbuilder.toString();
    }

    public static String lpad(int length, Long caseNumber) {
        String f = "%0" + length + "d";
        return String.format(f, caseNumber);
    }

    public static void main(String[] args) {

        System.out.println(formatNumberToCurrency("12345.135"));
    }
}
