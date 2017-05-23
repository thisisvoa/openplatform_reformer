package cn.com.reformer.common.util;

import java.io.UnsupportedEncodingException;

/**
 * 功能描述:转码UTF-8
 * <p/>
 * 版权所有：杭州立方控股
 * <p/>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @author jiqinwei 新增日期：2016/9/23
 * @author jiqinwei 修改日期：2016/9/23
 * @version 1.0.0
 * @since 1.0.0
 */
public class ToUTF8 {
    public static String toUTF8(String str) {
        if (str == null){
            return null;
        }
        String retStr = str;
        byte b[];
        try {
            b = str.getBytes("ISO8859_1");
            for (int i = 0; i < b.length; i++) {
                byte b1 = b[i];
                if (b1 == 63){
                    break; // 1
                } else if (b1 > 0){
                    continue;// 2
                }else if (b1 < 0) { // 不可能为0，0为字符串结束符
                    // 小于0乱码
                    retStr = new String(b, "UTF8");
                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
            // e.printStackTrace();
        }
        return retStr;
    }
}
