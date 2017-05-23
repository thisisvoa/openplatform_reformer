package cn.com.reformer.web.controller;

import java.math.BigInteger;

/**
 * 功能描述:
 * <p/>
 * 版权所有：杭州立方控股
 * <p/>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @author jiqinwei 新增日期：2017/4/5
 * @author jiqinwei 修改日期：2017/4/5
 * @version 1.0.0
 * @since 1.0.0
 */
public class TestBigInteger {

    public static void main(String[] args) throws Exception{
        //IntTenToSixTeen(110);
        //IntTenToTwo(10);
        //HexTenToSixTeen("110");
        //HexTenToTwo("10");
        SixTeenToTwo("10");
    }

    public static void IntTenToSixTeen(int ten){
        System.out.println("十进制10转16进制为" + Integer.toHexString(ten));
    }

    public static void IntTenToTwo(int ten){
        System.out.println("十进制10转二进制为"+Integer.toBinaryString(ten));
    }

    public static void HexTenToSixTeen(String Hexten){
        System.out.println("字符串10转16进制为"+Integer.toHexString(Integer.parseInt(Hexten)));
    }

    public static void HexTenToTwo(String Hexten){
        System.out.println("字符串10转二进制为"+Integer.toBinaryString(Integer.parseInt(Hexten)));
    }

    public static void SixTeenToTwo(String HexSixTeen){
        BigInteger srch = new BigInteger(HexSixTeen, 16);

        System.out.println("十六进制字符串 转为10进制后为:"+srch.toString());//转换为10进制并输出结果
        System.out.println("十六进制字符串 转为2进制后为"+Integer.toBinaryString(Integer.parseInt(srch.toString())));
    }
}
