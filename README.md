# java_emoji
java emoji unicode convert,emoji filter

项目要求jdk8



使用的emoji列表地址 https://unicode.org/Public/emoji/12.0/emoji-data.txt

使用前提  
    ***`/src/main/resources/emoji-data.txt`存储了emoji文件的列表，需要将这个导入到项目中***

功能:  
1.emoji unicode字符串转化为java识别的utf8字符串 `Emoji.unicodeToUTF8()`  
2.获取所有的emoji字符串列表 `EmojiFile.emojiList()`  
3.过滤给定字符串的所有emoji  `EmojiFilter.emojiFilter()`  
