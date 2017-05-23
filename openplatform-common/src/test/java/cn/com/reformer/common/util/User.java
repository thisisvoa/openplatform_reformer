package cn.com.reformer.common.util;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Since;

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
public class User {

    @SerializedName("pwd")
    private String password;
    @Expose
    @SerializedName("uname")
    private String username;
    @Expose
    @Since(1.1)
    private String gender;
    @Expose
    @Since(1.0)
    private String sex;

    //省略get,set方法


    public User(String password, String username, String gender, String sex) {
        this.password = password;
        this.username = username;
        this.gender = gender;
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
