import com.openplatform.weasel.sdk.helper.HttpClientUtil;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Administrator on 2017/6/8.
 */
public class HttpClientTest {

    public static void main(String[] args) {

        getRequestTest();
        getRequestTest2();
        postRequestTest();
    }

    private static void getRequestTest() {

        String url = "http://localhost:8089/openplatform-server/oauth/authorize?client_id=865005230406&response_type=code&redirect_uri=test/test.do";
        try {
            String str = HttpClientUtil.doGet(url, "UTF-8");
            if (str != null) {
                System.out.println("http Get request result:" + str);
            } else {
                System.out.println("http Get request process fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getRequestTest2() {

        String url = "http://localhost:8080/openplatform-server/oauth/authorize?client_id=065021683200&response_type=code&redirect_uri=weibo/aa.do";
        try {
            String str = HttpClientUtil.doGet2(url, "UTF-8");
            if (str != null) {
                System.out.println("http Get request result:" + str);
            } else {
                System.out.println("http Get request process fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void postRequestTest() {

        String url = "http://localhost:8080/SpringMVC/processing";

        Map<String, Object> _params = new HashMap<String, Object>();
        _params.put("name", "zhangshang");
        _params.put("age", 25);
        String str = HttpClientUtil.doPost(url, _params, "UTF-8", true);
        if (str != null) {
            System.out.println("http Post request result:" + str);
        } else {
            System.out.println("http Post request process fail");
        }
    }
}
