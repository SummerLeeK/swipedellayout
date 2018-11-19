package com.fudaoculture.lee.lib;


import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Al {

    public static void main(String[] args) {
        int i = 166;
        byte b = 127;

        byte c = (byte) i;
        String text = "[色][酷][酷]";

        String regex = "\\[[a-zA-Z0-9\\/\\u4e00-\\u9fa5]+\\]";
        ArrayList<String> ss =getAllSatisfyStr(text,regex);
        for (String s : ss) {
            System.out.println(s);
            System.out.println("");
        }


        System.out.println(text.indexOf("[酷]"));
        System.out.println(text.indexOf("[酷]",5));
    }

    /**
     * 获取所有满足正则表达式的字符串
     * @param str 需要被获取的字符串
     * @param regex 正则表达式
     * @return 所有满足正则表达式的字符串
     */
    private static ArrayList<String> getAllSatisfyStr(String str, String regex) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        ArrayList<String> allSatisfyStr = new ArrayList<>();
        if (regex == null || regex.isEmpty()) {
            allSatisfyStr.add(str);
            return allSatisfyStr;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            allSatisfyStr.add(matcher.group());
        }
        return allSatisfyStr;
    }
}