package cn.com.reformer.web.controller;

import cn.com.reformer.web.util.HttpUtils;
import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.Assertion;

import java.io.IOException;

/**
 * 功能描述:
 * <p/>
 * 版权所有：杭州立方控股
 * <p/>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @author jiqinwei 新增日期：2017/4/1
 * @author jiqinwei 修改日期：2017/4/1
 * @version 1.0.0
 * @since 1.0.0
 */
public class Test {
    public Assertion assertion;
    @BeforeClass
    public void beforeClass() {
        assertion = new Assertion();
    }

    @BeforeMethod
    public void runBeforeMethod() {
        HttpUtils.OpenHttpClient();
    }

    @org.testng.annotations.Test
    public void f() throws ClientProtocolException, IOException {
        String enterTrainningUrl = "http://192.168.30.69:8080/payment/test";
//        String enterObj = (new HttpUtils()).visitUrl(enterTrainningUrl);
        String enterObj = HttpUtils.sendPostHandler(enterTrainningUrl,"");
        System.out.println(enterObj.toString());
        assertion.assertTrue(enterObj.equals("success"));
    }
}
