package cn.com.reformer.common.util;

import java.util.*;

/**
 * 功能描述:
 * <p/>
 * 版权所有：杭州立方控股
 * <p/>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @author jhon yang 新增日期：2015-10-09
 * @author jhon yang 修改日期：2015-10-09
 * @version 1.0.0
 * @since 1.0.0
 */
public class Utils
{
    /**
     * 把int类型的值转为String类型
     *
     * @param value  int值
     * @param length 返回的字符串长度，不足补0;若length小于1则不补0
     * @return
     */
    public static String convertInt2String(int value, int length)
    {
        String str = value + "";
        if (length < 1)
            return str;

        if (str.length() >= length)
            return str;

        int count = length - str.length();
        String prefix = "";
        for (int i = 0; i < count; i++)
        {
            prefix += "0";
        }
        return prefix + str;
    }

    /**
     * 获取固定长度的字符串
     *
     * @param value
     * @param length 返回的字符串长度，不足补0;若length小于1则不补0
     * @return
     */
    public static String getFixLengthString(String value, int length)
    {
        if (value == null)
            return "";

        String str = value;
        if (length < 1)
            return str;

        if (str.length() >= length)
            return str;

        int count = length - str.length();
        String prefix = "";
        for (int i = 0; i < count; i++)
        {
            prefix += "0";
        }
        return prefix + str;
    }

    public static String reverseString(String s)
    {
        return new StringBuilder(s).reverse().toString();
    }

    public static Set<List<Integer>> getBatchIDs(List<Integer> idList, int size)
    {
        Set<List<Integer>> set = new HashSet<List<Integer>>();
        int length = idList.size();
        if (length <= size)
        {
            set.add(idList);
            return set;
        }

        List<Integer> ids = null;
        int count = 0;
        for (int i = 0; i < length; i++)
        {
            if (i == size * count)
            {
                ids = new ArrayList<Integer>();
                set.add(ids);
                count++;
            }
            ids.add(idList.get(i));
        }
        return set;
    }

    public static Set<List<Integer>> getBatchIDs(Integer[] ids_array, int size)
    {
        Set<List<Integer>> set = new HashSet<List<Integer>>();
        int length = ids_array.length;
        if (length <= size)
        {
            set.add(Arrays.asList(ids_array));
            return set;
        }

        List<Integer> ids = null;
        int count = 0;
        for (int i = 0; i < length; i++)
        {
            if (i == size * count)
            {
                ids = new ArrayList<Integer>();
                set.add(ids);
                count++;
            }
            ids.add(ids_array[i]);
        }
        return set;
    }
}
