package cn.com.reformer.common.util;

import java.util.Random;

/**
 * Created by feixiang on 2016-09-21.
 */
public class ShareCodeUtil {

    /**
     * 生成邀请码
     * @return
     */
    public static String shareCode(){
        String randomStr = "123456789abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder invitateCode = new StringBuilder("");
        for (int i = 0; i < 6; i++) {
            invitateCode.append(randomStr.charAt(random.nextInt(randomStr.length())));
        }
        return invitateCode.toString().toUpperCase();
    }


    public static void main(String[] args) {
        for(int i=0;i<1111;i++){

            String code =shareCode();
            System.out.println(code);
        }
    }
}
