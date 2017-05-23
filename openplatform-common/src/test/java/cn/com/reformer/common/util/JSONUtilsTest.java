package cn.com.reformer.common.util;

/**
 * 功能描述:
 * <p/>
 * 版权所有：杭州立方控股
 * <p/>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @author zhangjin 新增日期：15-10-16
 * @author 你的姓名 修改日期：15-10-16
 * @version 1.0.0
 * @since 1.0.0
 */
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JSONUtilsTest {

    public static void main(String[] args) {
        //jsonToObject();
        //objectToJson2();
        objectToJson();
    }

    static void objectToJson() {

        //不使用注解的方式
        List<User2> userList = new LinkedList<User2>();
        userList.add(new User2("david", 34, true));
        userList.add(new User2("jack", 31, false));
        Type targetType = new TypeToken<List<User2>>(){}.getType();
        Type targetType1 = new TypeToken<User2>(){}.getType();
        //sUserList1: [{},{}]
        User2 jack = new User2("jack", 31, false);
        String aa = JSONUtils.toJson(jack, targetType1,false);
        System.out.println("sUserList1: " + aa);

        //sUserList2: [{"id":23,"name":"david","age":34,"sex":true},{"id":21,"name":"jack","age":31,"sex":false}]
        String sUserList2 = JSONUtils.toJson(userList, targetType, false);
        System.out.println("sUserList2: " + sUserList2);

        //sUserList3: [{},{}]
        String sUserList3 = JSONUtils.toJson(userList, targetType, 1.0d, true);
        System.out.println("sUserList3: " + sUserList3);

        //原生GSON方式


    }

    static void objectToJson2() {
        //使用注解的方式提取
        List<User> userList = new LinkedList<User>();
        User jack = new User("Jack", "123456", "Male","Female");
        User marry = new User("Marry", "888888", "Female","Male");
        userList.add(jack);
        userList.add(marry);
        Type targetType = new TypeToken<List<User>>() {}.getType();

        String sUserList1 = JSONUtils.toJson(userList, targetType);
        System.out.println("sUserList1: " + sUserList1);
        // sUserList1 ----> [{"uname":"jack","gender":"Male","sex":"Male"},{"uname":"marry","gender":"Female","sex":"Female"}]
        String sUserList2 = JSONUtils.toJson(userList, targetType, false);
        System.out.println("sUserList2: " + sUserList2);
        // sUserList2 ----> [{"uname":"jack","pwd":"123456","gender":"Male","sex":"Male"},{"uname":"marry","pwd":"888888","gender":"Female","sex":"Female"}]
        String sUserList3 = JSONUtils.toJson(userList, targetType, 1.0d, true);
        System.out.println("sUserList3: " + sUserList3);
        // sUserList3 ----> [{"uname":"jack","sex":"Male"},{"uname":"marry","sex":"Female"}]

        //原生GSON方式
        Gson gson = new Gson();
        sUserList1 = gson.toJson(userList, targetType);
        System.out.println("sUserList-gson: " + sUserList1);
    }

    @SuppressWarnings("unchecked")
    static void jsonToObject() {

        /**
         *  sUserList1: [{"uname":"123456","gender":"Male","sex":"Female"},{"uname":"888888","gender":"Female","sex":"Male"}]
         sUserList2: [{"pwd":"Jack","uname":"123456","gender":"Male","sex":"Female"},{"pwd":"Marry","uname":"888888","gender":"Female","sex":"Male"}]
         sUserList3: [{"uname":"123456","sex":"Female"},{"uname":"888888","sex":"Male"}]
         * */
        List<User> userList = new LinkedList<User>();
        User jack = new User("123456","Jack", "Male","Female");
        User marry = new User("888888","Marry", "Female","Male");
        userList.add(jack);
        userList.add(marry);
        Type targetType = new TypeToken<List<User>>() {}.getType();
        String sUserList2 = JSONUtils.toJson(userList, targetType, false);

        //使用了注解的方式提取:List,它会优化提取注解名称
        String json = sUserList2;// "[{pwd:'223232323',uname:'jack',gender:'Male',sex:'Male'},{pwd:'5757567',uname:'marry',gender:'Female',sex:'Female'}]";
        List<User> users = JSONUtils.fromJson(json, new TypeToken<List<User>>(){});
        for(User user : users){
            System.out.println("user: "+user);
        }
        System.out.println("============================");
        //直接提取:List
        json = "[{name:'jack',age:18,sex:true},{name:'david',age:24,sex:false}]";
        List<User2> users2 = JSONUtils.fromJson(json, new TypeToken<List<User2>>(){});
        for(User2 user : users2){
            System.out.println("user: "+user);
        }
        //使用了注解的方式提取:User
        System.out.println("============================");
        json = "{pwd:'6666666',uname:'david',gender:'Male',sex:'Male'}";
        User user = JSONUtils.fromJson(json, User.class);
        System.out.println("user: "+user);

        //直接提取:User
        System.out.println("============================");
        json = "{name:'jack',age:18,sex:true}";
        User2 user2 = JSONUtils.fromJson(json, User2.class);
        System.out.println("user: "+user2);


        //原生GSON方式
        Gson gson = new Gson();
        sUserList2 = "[{pwd:'Jack',uname:'123456',gender:'Male',sex:'Female'},{pwd:'Marry',uname:'888888',gender:'Female',sex:'Male'}]";
        List<User> users3 = gson.fromJson(sUserList2,targetType);
        for(User user3_1 : users3){
            System.out.println("原生方式: "+user3_1);
        }

        List<User2> userList4 = new LinkedList<User2>();
        userList4.add(new User2("david", 34, true));
        userList4.add(new User2("jack", 31, false));
        Type targetType4 = new TypeToken<List<User2>>(){}.getType();
        sUserList2 = gson.toJson(userList4, targetType4);

        List<User2> users4 = gson.fromJson(sUserList2,targetType4);
        for(User2 user4_1 : users4){
            System.out.println("原生方式2: "+user4_1);
        }

    }
}
