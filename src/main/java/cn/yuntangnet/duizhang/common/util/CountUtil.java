package cn.yuntangnet.duizhang.common.util;

/**
 * @author 张茂林
 * @since 2017/12/25 14:38
 */
public class CountUtil {

    public static String int2AAA(int x) {
        if (x < 0)
            return "";

        int a = x / 26;
        int b = x % 26;
        return int2AAA(a - 1) + (char) (b + 'A');
    }

    public static String int2AAA2(int valueInt) {
        int x = valueInt / 100;
        int y = valueInt % 100;

        String s = int2AAA(x);
        if (y < 10) {
            s += "0" + y;
        } else {
            s += y;
        }
        return s;
    }

    public static int aaa2Int(String x) {
        return AAA(x, x.length() - 1);
    }

    private static int AAA(String x, int end) {
        char[] chars = x.toCharArray();
        int n = chars[end] - 'A';
        if (end == 0)
            return n;
        return (AAA(x, end - 1) + 1) * 26 + n;
    }
}
