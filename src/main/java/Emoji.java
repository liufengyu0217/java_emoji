import java.io.UnsupportedEncodingException;

/**
 * @author jianganlan
 * emoji
 */
public class Emoji {

    /**
     * unicode 编码字符串转化为UTF8字符串
     *
     * @param unicodeString unicode 字符串
     * @return UTF8字符串
     */
    public static String unicodeToUTF8(String unicodeString) {
        int num = hexStringToInt(unicodeString);
        byte[] bytes = unicodeIntToByte(num);
        String s = utf8String(bytes);
        return s;
    }


    /**
     * 16位unicode 字符串转化为数字(十进制)
     *
     * @param hexString 16位unicode 字符串
     * @return 字符串转化为数字
     */
    private static int hexStringToInt(String hexString) {
        return Integer.parseInt(hexString, 16);
    }


    /**
     * unicode int 转化为 UTF-32 格式的字符串byte数组
     * <p>
     * 每2个字节（16位）合并成一个byte
     * <p>
     * java char实现有关
     *
     * @param unicodeInt
     * @return
     */
    private static byte[] unicodeIntToByte(int unicodeInt) {
        byte[] result = new byte[4];
        result[0] = (byte) ((unicodeInt >>> 24) & 0xff);
        result[1] = (byte) ((unicodeInt >>> 16) & 0xff);
        result[2] = (byte) ((unicodeInt >>> 8) & 0xff);
        result[3] = (byte) ((unicodeInt) & 0xff);
        return result;
    }

    /**
     * UTF-32格式byte数组转化为utf8字符串
     *
     * @param bytes utf32格式的字符数组
     * @return utf8格式字符串
     */
    private static String utf8String(byte[] bytes) {
        try {
            return new String(bytes, "utf-32");
        } catch (UnsupportedEncodingException e) {
            return "";
        }

    }


}
