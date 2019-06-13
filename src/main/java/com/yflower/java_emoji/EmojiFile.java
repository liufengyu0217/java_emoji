package com.yflower.java_emoji;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author jianganlan
 * emoji文件处理
 * <p>
 * emoji官方文件目录:             https://unicode.org/Public/emoji/
 * 当前resource下使用的版本v12地址 https://unicode.org/Public/emoji/12.0/emoji-data.txt
 */
public class EmojiFile {
    public static List<String> emojiList() {
        URL resource = EmojiFile.class.getResource("emoji-data.txt");

        try {
            URI uri = resource.toURI();
            Path path = Paths.get(uri);
            List<String> emojiString = Files.lines(path)
                    .filter(line -> !line.trim().isEmpty())
                    .filter(line -> !line.startsWith("#"))
                    .flatMap(line -> {
                        //定位第一个空格
                        int firstBlankChar = line.indexOf(" ");
                        if (firstBlankChar <= 0) {
                            return Stream.empty();
                        }
                        String substring = line.substring(0, firstBlankChar);

                        if (substring.isEmpty()) {
                            return Stream.empty();
                        }

                        //区间范围
                        if (substring.contains(".")) {
                            return splitString(substring).stream();
                        } else {
                            return Stream.of(substring);
                        }


                    }).distinct().collect(Collectors.toList());

            //过滤#
            emojiString.remove("0023");
            //过滤*
            emojiString.remove("002A");
            //过滤数字
            splitString("0030..0039").forEach(emojiString::remove);

            return emojiString;


        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }


    /**
     * 将 1F481..1F483 区间转化为列表
     *
     * @param unicodeString unicode字符串区间
     * @return unicode字符串列表
     */
    private static List<String> splitString(String unicodeString) {
        String regex = "\\.\\.";
        Pattern pattern = Pattern.compile(regex);
        String[] strings = pattern.split(unicodeString);

        if (strings == null || strings.length != 2) {
            return new ArrayList<>();
        }

        String start = strings[0];
        String end = strings[1];

        int si = Integer.parseInt(start, 16);
        int ei = Integer.parseInt(end, 16);

        List<String> result = new ArrayList<>();
        for (int i = si; i <= ei; ++i) {
            result.add(Integer.toHexString(i));
        }

        return result;
    }

}
