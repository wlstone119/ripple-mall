package com.stone.ripple.util.tool;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@SuppressWarnings("deprecation")
public class HttpClientUtil {
    private static final String CHARSET = HTTP.UTF_8;
    private static HttpClient customerHttpClient;

    private HttpClientUtil() {
    }

    public static synchronized HttpClient getHttpClient() {
        if (null == customerHttpClient) {
            HttpParams params = new BasicHttpParams();
            // 设置一些基本参数
            HttpProtocolParams.setContentCharset(params, CHARSET);
            HttpProtocolParams.setUseExpectContinue(params, true);
            HttpProtocolParams.setUserAgent(params,
                    "Mozilla/5.0(Linux;U;Android 2.2.1;en-us;Nexus One Build.FRG83) " + "AppleWebKit/553.1(KHTML,like Gecko) Version/4.0 Mobile Safari/533.1");
            /* 超时设置, 从连接池中取连接的超时时间 */
            ConnManagerParams.setTimeout(params, 1000);
            /* 连接超时 */
            HttpConnectionParams.setConnectionTimeout(params, 2000);
            /* 请求超时 */
            HttpConnectionParams.setSoTimeout(params, 4000);

            // 设置我们的HttpClient支持HTTP和HTTPS两种模式
            SchemeRegistry schReg = new SchemeRegistry();
            schReg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            schReg.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));

            // 使用线程安全的连接管理来创建HttpClient
            ClientConnectionManager conMgr = new ThreadSafeClientConnManager(params, schReg);
            customerHttpClient = new DefaultHttpClient(conMgr, params);
        }
        return customerHttpClient;
    }

    /**
     * post请求
     *
     * @param url
     * @param params
     * @return
     */
    public static String post(String url, JSONObject jsonParam) {
        String result = null;

        HttpResponse response = null;
        try {
            // 创建POST请求
            HttpPost request = new HttpPost(url);
            if (jsonParam != null) {
                StringEntity entity = new StringEntity(jsonParam.toString(), CHARSET);
                entity.setContentEncoding(CHARSET);
                entity.setContentType("application/json");
                request.setEntity(entity);
            }
            
            // 发送请求
            response = getHttpClient().execute(request);
        } catch (Exception e) {
        }

        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
        }

        try {
            HttpEntity resEntity = response.getEntity();
            result = (resEntity == null) ? null : EntityUtils.toString(resEntity, CHARSET);
        } catch (Exception e) {
        }

        return result;
    }

    public static void main(String[] args) throws Exception {

        String url = "http://serviceplatcontrol.wdai.com/crawler/educationcollect";

        JSONObject param = new JSONObject();
        JSONObject obj = new JSONObject();
        obj.put("password", "1234567");
        obj.put("captcha", "");
        obj.put("action", "doLogin");
        obj.put("accountNumber", "13123933947");
        obj.put("smsCode", "");
        
        param.put("data", JSON.toJSONString(obj));
        param.put("serialId", "");
        param.put("orderId", "");
        param.put("token", "e169e81f572c4a02adf86b85df9b63aa");

        System.out.println(post(url, param));
    }
}
