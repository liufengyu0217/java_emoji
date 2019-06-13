package com.yflower.java_emoji;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jianganlan
 * 字符串emoji过滤
 */
public class EmojiFilter {

    private static List<String> EMOJI_STRING_LIST = EmojiFile.emojiList().stream().map(Emoji::unicodeToUTF8).collect(Collectors.toList());


    public static String emojiFilter(String s) {
        String filterString = s;
        for (String emojiString : EMOJI_STRING_LIST) {
            filterString = filterString.replace(emojiString, "");
        }

        return filterString;
    }

    public static void main(String[] args) {
        System.out.println("Hi, I am fine. \uD83D\uDE01");
        System.out.println(EmojiFilter.emojiFilter("Hi, I am fine. \uD83D\uDE01"));
    }
}
