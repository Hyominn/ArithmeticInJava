package com.op.algorithm;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: NZY
 * @Date: 2020/5/25 17:31
 */
public class ComputerCount {

    public static void main(String[] args){
        String str = "aaabbbcccdfb";
        compress(str);
    }

    // 计算字符串中某个字符出现的次数
    public static void compress(@NotNull String str) {
        Map<String, Integer> count = new HashMap<String, Integer>();
        String[] myStrs = str.split("");
        for (String myStr : myStrs) {
            int totalNum = 1;
            if (count.containsKey(myStr)) {
                totalNum = count.get(myStr) + 1;
            }
            count.put(myStr, totalNum);
        }
        int num = count.size();
        System.out.println("压缩结果");
        if (num == myStrs.length) {
            System.out.println("各个字母都不一样，直接返回");
            System.out.println(str);
            return;
        }
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Integer> entry : count.entrySet()) {
            result.append(entry.getKey()).append(entry.getValue());
        }
        System.out.println(result);
    }
}
