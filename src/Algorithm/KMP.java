package Algorithm;

import java.util.ArrayList;

public class KMP {
    //获得pattern在s中出现的所有下标
    public static java.util.List<Integer> KMP(String s, String pattern) {
        java.util.List<Integer> result = new ArrayList<Integer>();
        int pi[] = compute_prefix(pattern);
        int k = -1;//p[0]到p[k]已经匹配成功
        for (int i = 0; i < s.length(); i++) {
            while (k >= 0 && s.charAt(i) != pattern.charAt(k + 1))
                k = pi[k];
            if (s.charAt(i) == pattern.charAt(k + 1)) k++;
            if (k == pi.length - 1) {
                result.add(i - pattern.length() + 1);
                k = pi[k];
            }
        }
        return result;
    }

    public static int[] compute_prefix(String pattern) {
        int[] pi = new int[pattern.length()];
        pi[0] = -1;
        int k = -1;//E(i-1)的最大值,E(i)表示所有k<i，pk为pi的后缀,若E(i-1)为空则为-1
        for (int i = 1; i < pattern.length(); i++) {
            while (k >= 0 && pattern.charAt(k + 1) != pattern.charAt(i))
                k = pi[k];
            if (pattern.charAt(k + 1) == pattern.charAt(i)) k++;
            pi[i] = k;
        }
        return pi;
    }
}
