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
public class User2 {

    private String name = null;
    private int age = 0;
    private boolean sex = false;
    //省略get,set方法


    public User2(String name, int age, boolean sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }
}
