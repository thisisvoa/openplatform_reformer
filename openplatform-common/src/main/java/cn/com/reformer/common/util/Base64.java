package cn.com.reformer.common.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * 功能描述:
 * <p/>
 * 版权所有：杭州立方控股
 * <p/>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @author jiqinwei 新增日期：2016/9/26
 * @author jiqinwei 修改日期：2016/9/26
 * @version 1.0.0
 * @since 1.0.0
 */
public class Base64 {
    public static String GetImageStr(String imgFilePath) {// 将文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data = null;
        // 读取字节数组
        try {
            InputStream in = new FileInputStream(imgFilePath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(data==null){
            return null;
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
    }
    public static boolean GenerateImage(String imgStr, String imgFilePath) {// 对字节数组字符串进行Base64解码并生成
        if (imgStr == null || imgStr.equals("")) // 数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] bytes = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            // 生成
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(bytes);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        String strImg = "";
        // 测试从图片文件转换为Base64编码
        String path = "D:\\qwe.png";
        if(path!=null &&!("".equals(path))){
            strImg = GetImageStr(path);
            System.out.println(strImg);
        }
        // 测试从Base64编码转换为图片文件
        GenerateImage(strImg, "D:\\asd.png");
        System.out.println("\n over");
    }

}
