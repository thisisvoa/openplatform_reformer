import com.openplatform.weasel.sdk.DefaultPlatformClient;
import com.openplatform.weasel.sdk.OpenPlatformOauthConfig;
import com.openplatform.weasel.sdk.request.PlatformAccesssTokenRequest;
import com.openplatform.weasel.sdk.response.PlatformAccessTokenResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/27.
 */
public class Test {
    public static void main(String[] args) {

        DefaultPlatformClient client = new DefaultPlatformClient();
        PlatformAccesssTokenRequest request = new PlatformAccesssTokenRequest();
        request.setAppkey("865005230406");
        request.setSecret("VivioGkUYs8DJqOrK34m25MBHDTr");
        request.setRedirectUrl("test/test.do");
        request.setCode("4839dafa8ae48eb69d39f98b79df653f");
        request.setGrantType(GrantType.AUTHORIZATION_CODE);
        try {

            Map<String,String> stringMaps=new HashMap<String,String>();
            stringMaps.put("appkey",request.getAppkey());
            stringMaps.put("secret",request.getSecret());
            stringMaps.put("redirectUrl",request.getRedirectUrl());
            //http://localhost:8080/openplatform-server/oauth/authorize?client_id=065021683200&response_type=code&redirect_uri=weibo/aa.do
            String url = OpenPlatformOauthConfig.authorizeUrl + "?client_id=" + request.getAppkey() + "&response_type=code&redirect_uri=" + request.getRedirectUrl();
            String code_url =HttpClientUtil2.httpGetRequest(url);

            String code=code_url.substring(code_url.indexOf("="));
            request.setCode(code);
            PlatformAccessTokenResponse response = client.getAccessToken(request);

            PlatformAccessTokenResponse platformAccessTokenResponse = client.getRefreshAccessToken(request);
            String token = platformAccessTokenResponse.getAccess_token();
        } catch (OAuthSystemException e) {
            e.printStackTrace();
        } catch (OAuthProblemException e) {
            e.printStackTrace();
        }
    }




}
