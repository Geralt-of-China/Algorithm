package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//30. Substring with Concatenation of All Words
public class P30 {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (words.length == 0) return result;
        int wordLength = words[0].length();//获得单词的长度，可能为0
        int totalLength = words.length * wordLength;//获得匹配总长度
        HashMap<String, Integer> map = new HashMap<>();
        for (String str :
                words) {
            Integer temp = map.get(str);
            if (temp == null) map.put(str, 1);
            else map.put(str, temp + 1);
        }
        tag:
        for (int i = 0; i <= s.length() - totalLength; i++) {
            HashMap<String, Integer> seen = (HashMap<String, Integer>) map.clone();
            String temp = s.substring(i, i + totalLength);
            for (int j = 0; j < words.length; j++) {
                String part = temp.substring(j * wordLength, (j + 1) * wordLength);
                Integer count = seen.get(part);
                if (count == null) continue tag;
                else {
                    seen.put(part, count - 1);
                }
            }
            boolean flag = true;//当前i是否符合
            for (Map.Entry<String, Integer> entry :
                    seen.entrySet()) {
                if (entry.getValue() != 0) flag = false;
            }
            if (flag) result.add(i);
        }
        return result;
    }
}
