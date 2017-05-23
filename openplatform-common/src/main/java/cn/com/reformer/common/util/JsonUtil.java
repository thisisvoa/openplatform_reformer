package cn.com.reformer.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * Created by CHAO on 2017/3/2.
 */
public class JsonUtil {

    public static <T> T parseObject(String text, Class<T> cls) {
        T object;
        try {
            object = JSONObject.parseObject(text, cls, new Feature[0]);
        } catch (Exception e) {
            return null;
        }
        return object;
    }

    public static String toJSONString(Object obj) {
        return JSONObject.toJSONString(obj);
    }

    public static JSONObject parseObject(String txt) {
        return JSONObject.parseObject(txt);
    }

    public static void main(String[] args) {
        Map<String, Integer> map = new HashedMap();
        map.put("age", 12);
        map.put("tall", 180);
        String string = toJSONString(map);
        Map<String, Integer> jsonString = parseObject(string, Map.class);
        return;
    }
}
