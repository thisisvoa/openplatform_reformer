package cn.com.reformer.common.util;

import java.io.OutputStream;
import java.security.MessageDigest;
import java.util.Map;
import java.util.Properties;

/**
 * Created by feixiang on 2016-09-09.
 */
public class MD5 {

    public static String byteArrayToHex(byte[] b) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
                'b', 'c', 'd', 'e', 'f' };
        char[] ob = new char[2];
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            ob[0] = Digit[(b[i] >>> 4) & 0X0F];
            ob[1] = Digit[b[i] & 0X0F];
            s.append(new String(ob));
        }
        return s.toString();
    }

    public static String byteArrayToHexTemp(byte[] b) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
                'b', 'c', 'd', 'e', 'f' };
        char[] ob = new char[2];
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            ob[0] = Digit[(b[i] >>> 4) & 0X0F];
            ob[1] = Digit[b[i] & 0X0F];
            if (ob[0] == '0') {
                ob[0] = ' ';
            }
            s.append(new String(ob).trim());
        }
        return s.toString();
    }

    public static String Sha1(String str) {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            return byteArrayToHex(sha.digest(str.getBytes()));
        } catch (Exception e) {
            return null;
        }
    }

    public static String md5(String str) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            return byteArrayToHex(md5.digest(str.getBytes("UTF-8")));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 为了适配原先C#中的算法缺陷，临时使用的指纹算法。
     *
     * @param str
     * @return
     */
    public static String md5Temp(String str) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            return byteArrayToHexTemp(md5.digest(str.getBytes("UTF-16LE")));
        } catch (Exception e) {
            return null;
        }
    }

    public static void write(Properties prop, OutputStream os, String encoding)
            throws Exception {
        StringBuffer sb = new StringBuffer();
        MessageDigest md = MessageDigest.getInstance("MD5");
        for (Map.Entry<Object, Object> key : prop.entrySet()) {
            String name = key.getKey().toString();
            String value = key.getValue().toString();
            String line = name + "=" + value + "\r\n";
            md.update(line.getBytes(encoding));
            sb.append(line);
        }
        String line = "#" + MD5.byteArrayToHex(md.digest()) + "\r\n";
        os.write(line.getBytes(encoding));
        os.write(sb.toString().getBytes(encoding));
    }

    public static void main(String[] args) throws Exception {
        System.out.println(MD5.md5Temp("123456"));
    }
}
