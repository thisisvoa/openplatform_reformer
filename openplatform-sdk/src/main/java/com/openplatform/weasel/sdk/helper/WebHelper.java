/**
 * HttpClient.java
 */
package com.openplatform.weasel.sdk.helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.openplatform.weasel.sdk.exception.IOCommunicateException;

/**http客户端
 * @author Dylan
 * @time 2013-3-16
 */
public final class WebHelper {

	public final static HttpClient client = new HttpClient();
	

	private WebHelper(){}
	/**
	 * 
	 * @param hostConfiguration
	 */
	public void setHostConfiguration(HostConfiguration hostConfiguration){
		client.setHostConfiguration(hostConfiguration);
	}
	
	/**
	 * 
	 * @param httpConnectionManager
	 */
	public void setHttpConnectionManage(HttpConnectionManager httpConnectionManager){
		client.setHttpConnectionManager(httpConnectionManager);
	}
	
	/**
	 * 
	 * @param cookie
	 */
	public void addCookie(Cookie cookie){
		client.getParams().setCookiePolicy(CookiePolicy.RFC_2109);
		HttpState initialState = new HttpState();
		initialState.addCookie(cookie);
		client.setState(initialState);
	}
	
	public Cookie[] getCookie(){
		return client.getState().getCookies();
	}
	
	/**
	 * 
	 * @param method
	 * @param url
	 * @param headers
	 * @param params
	 * @return
	 */
	private static HttpMethod execute(Method method, String url, Map<String, String> headers, Map<String, String> params,Map<String,String> queryString) {
		
		HttpMethod httpMethod = method.getMethod();
		try {
			URI uri = new URI(url, true, "utf-8");
			httpMethod.setURI(uri);
			httpMethod.setParams(createParams(params));
			httpMethod.setQueryString(createNameValuePair(queryString));
			initHeader(httpMethod, headers);
			client.executeMethod(httpMethod);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return httpMethod;
	}
	
	/**
	 * 
	 * @param method
	 * @param url
	 * @param clazz
	 * @return
	 */
	public static <T> T execute(Method method,String url,Class<T> clazz){
		return execute(method, url,new HashMap<String,String>(), new HashMap<String,String>(), new HashMap<String,String>(),clazz);
	}
	/**
	 * 
	 * @param method and enum
	 * @param url
	 * @param headers
	 * @param params
	 * @param clazz
	 * @return
	 */
	public static <T> T execute(Method method,String url,Map<String,String> headers,Map<String,String> params,Map<String,String> queryString,Class<T> clazz){
		
		HttpMethod response = execute(method, url, headers, params,queryString);
		String json = "";
		try {
			json = response.getResponseBodyAsString();
		} catch (IOException e) {
			throw new IOCommunicateException(e.getMessage());
		}finally{ releaseConnection(response); }
		return JsonHelper.fromJsonString(json, clazz);
		
	}
	
	/**
	 * @param url
	 * @param params
	 * @param clazz
	 * @return
	 */
	public static <T> T doPost(String url,Map<String,String> params,Class<T> clazz){
		Map<String,String> headers = new HashMap<>();
		headers.put("Content-Type", "application/x-www-form-urlencoded");
		return execute(Method.POST, url, headers, params, params, clazz);
	}
	
	/**
	 * @param url
	 * @param queryString
	 * @param clazz
	 * @return
	 */
	public static <T> T doGet(String url,Map<String,String> queryString,Class<T> clazz){
		Map<String,String> headers = new HashMap<>();
		headers.put("Content-Type", "application/x-www-form-urlencoded");
		return execute(Method.GET, url, headers, new HashMap<>(), queryString, clazz);
	}
	
	/**
	 * 
	 * @param params
	 * @return
	 */
	private static HttpMethodParams createParams(Map<String,String> params){
		HttpMethodParams methodParams = new HttpMethodParams();
		methodParams.setContentCharset("utf-8");
		for(String key : params.keySet()){
			methodParams.setParameter(key, params.get(key));
		}
		return methodParams;
	}
	
	/**
	 * 
	 * @param params
	 * @return
	 */
	private static NameValuePair[] createNameValuePair(Map<String,String> params){
		
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		
		for(String key : params.keySet()){
			NameValuePair pair = new NameValuePair();
			pair.setName(key);
			pair.setValue(params.get(key));
			pairs.add(pair);
		}
		return pairs.toArray(new NameValuePair[]{});
	}
	
	/**
	 * 
	 * @param method
	 * @param headers
	 */
	private static void initHeader(HttpMethod method,Map<String,String> headers){
		
		for(String key : headers.keySet()){
			method.addRequestHeader(key, headers.get(key));
		}
	}
	
	private static void releaseConnection(HttpMethod method){
		if(null != method){
			method.releaseConnection();
		}
	}
	
	public static enum Method {

		GET(new GetMethod()), POST(new PostMethod()), PUT(new PutMethod()), DELETE(new DeleteMethod());

		private HttpMethod method;

		private Method(HttpMethod _method) {
			this.method = _method;
		}

		public HttpMethod getMethod() {
			return method;
		}

	}
}
