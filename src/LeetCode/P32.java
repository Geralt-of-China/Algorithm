package LeetCode;

import java.util.*;

//32. Longest Valid Parentheses
public class P32 {
    //内存消耗优于100%的解法
    public int longestValidParentheses(String s) {
        int leftcount = 0;
        HashMap<Integer, Integer> mem = new HashMap<>();
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') leftcount++;
            else {
                leftcount--;
                if (leftcount < 0) {
                    leftcount = 0;
                    int temp = getMax(mem.values());
                    max = temp > max ? temp : max;
                    mem.clear();
                    continue;
                }
                Integer currentCount = mem.get(leftcount + 1);
                mem.remove(leftcount + 1);
                int increment = currentCount == null ? 1 : currentCount + 1;
                Integer lastCount = mem.get(leftcount);
                if (lastCount == null) mem.put(leftcount, increment);
                else mem.put(leftcount, lastCount + increment);
            }
        }
        int temp = getMax(mem.values());
        max = temp > max ? temp : max;
        return max * 2;
    }

    private int getMax(Collection<Integer> set) {
        int max = 0;
        for (int i :
                set) {
            if (i > max) max = i;
        }
        return max;
    }

    //基于动态规划的第二种解法,速度超过100%
    public int longestValidParentheses2(String s) {
        if (s.length() < 2) return 0;
        int a[] = new int[s.length()];//a[i]表示以s[i]结尾的最大长度
        int max = 0;
        if (s.charAt(0) == '(' && s.charAt(1) == ')') {
            a[1] = 2;
            max = 2;
        }
        for (int i = 2; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') a[i] = a[i - 2] + 2;
                else {
                    if (i - a[i - 1] - 1 >= 0 && s.charAt(i - a[i - 1] - 1) == '(') {
                        if (i - a[i - 1] - 2 >= 0) a[i] = a[i - 1] + a[i - a[i - 1] - 2] + 2;
                        else a[i] = a[i - 1] + 2;
                    }
                }
                if (a[i] > max) max = a[i];
            }
        }
        return max;
    }
}
