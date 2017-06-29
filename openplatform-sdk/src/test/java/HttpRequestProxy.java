import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.*;

/**
 * Created by Chen Hongyu on 2016/5/17.
 */
public class HttpRequestProxy {
    private static Logger LOGGER = Logger.getLogger(HttpRequestProxy.class);

    /**
     * @param args
     * @throws IOException
     * @throws ClientProtocolException
     */
    public static void main(String[] args) throws ClientProtocolException, IOException {

        String urlPost = "http://localhost:8080/collectDataPost.do";
        String urlGet = "http://localhost:8080/collectDataGet.do?name=张三";

        Map<String, String> params = new HashMap<String, String>();
        params.put("name", "jerry");
        params.put("age", "18");
        params.put("sex", "man");
        String respon = doPost(urlPost, params);
        System.out.println("================发送请求：" + params);
        System.out.println("================回掉结果：" + respon);

    }

    public static String doGet(String url) {
        try {
            // 创建HttpClient实例
            HttpClient httpclient = new DefaultHttpClient();
            // 创建Get方法实例
            HttpGet httpgets = new HttpGet(url);
            HttpResponse response = httpclient.execute(httpgets);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instreams = entity.getContent();
                String str = convertStreamToString(instreams);
                System.out.println("Do something");
                System.out.println(str);
                // Do not need the rest
                httpgets.abort();
            }
        } catch (Exception e) {

        }
        return url;
    }

    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static String doPost(String url, Map<String, String> map) {

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost method = new HttpPost(url);
        method.setHeader("accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");

        int status = 0;
        String body = null;

        if (method != null & map != null) {
            try {
                //建立一个NameValuePair数组，用于存储欲传送的参数
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                //添加参数
                method.setEntity(new UrlEncodedFormEntity(params));

                long startTime = System.currentTimeMillis();

                HttpResponse response = httpClient.execute(method);

                System.out.println("the http method is:" + method.getEntity());
                long endTime = System.currentTimeMillis();
                int statusCode = response.getStatusLine().getStatusCode();
                LOGGER.info("状态码:" + statusCode);
                LOGGER.info("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));
                if (statusCode != HttpStatus.SC_OK) {
                    LOGGER.error("请求失败:" + response.getStatusLine());
                    status = 1;
                }

                //Read the response body
                body = EntityUtils.toString(response.getEntity(), "UTF-8");

            } catch (IOException e) {
                //发生网络异常
                //网络错误
                status = 3;
            } finally {
                LOGGER.info("调用接口状态：" + status);
            }

        }
        return body;
    }

}