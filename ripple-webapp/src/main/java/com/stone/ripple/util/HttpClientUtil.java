package com.stone.ripple.util;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * Created by xueyongfeng on 2017/9/4.
 */

@Component
public class HttpClientUtil {
	private final static Logger LOGGER = LoggerFactory.getLogger(HttpClientUtil.class);

	private int maxTotal = 200;// 默认最大连接数
	private int defaultMaxPerRoute = 75;// 默认每个主机的最大链接数
	private int connectionRequestTimeout = 3000;// 默认请求超时时间
	private int connectTimeout = 3000;// 默认链接超时时间
	private int socketTimeout = 6000;// 默认socket超时时间
	private CloseableHttpClient httpClient;

	public HttpClientUtil() {
		init();
	}

	public String sendGet(String url, Map<String, Object> params) throws IOException {
		String requestUrl = buildRequestUrl(url, params);
		HttpGet httpget = new HttpGet(requestUrl);
		config(httpget);
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpget, HttpClientContext.create());
			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity, "utf-8");
		} catch (IOException e) {
			throw e;
		}
	}

	private String buildRequestUrl(String url, Map<String, Object> params) {
		StringBuffer sb = new StringBuffer(url + "?");
		if (params != null && params.size() > 0) {
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
			}
		}
		return sb.toString();
	}

	private StringEntity buildStringEntity(Map<String, Object> params) { // TODO
																			// 异常好好整整
		if (params != null && params.size() > 0) {
			JSONObject json = new JSONObject(params);
			StringEntity stringEntity = new StringEntity(json.toString(), ContentType.APPLICATION_JSON);
			return stringEntity;
		}
		return null;
	}

	public String sendPost(String url, Map<String, Object> urlParams, Map<String, Object> bodyParams)
			throws IOException {
		if (urlParams != null && urlParams.size() > 0) {
			url = buildRequestUrl(url, urlParams);
		}

		HttpPost httpPost = new HttpPost(url);
		StringEntity stringEntity = buildStringEntity(bodyParams);
		if (stringEntity != null) {
			httpPost.setEntity(stringEntity);
		}
		config(httpPost);
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpPost, HttpClientContext.create());
			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity, "utf-8");
		} catch (IOException e) {
			throw e;
		}

	}

	private void init() {
		ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
		LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();
		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", plainsf).register("https", sslsf).build();
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
		// 设置最大连接数
		cm.setMaxTotal(maxTotal);
		// 设置每个路由的默认连接数
		cm.setDefaultMaxPerRoute(defaultMaxPerRoute);

		// 连接保持时间
		ConnectionKeepAliveStrategy myStrategy = new ConnectionKeepAliveStrategy() {
			public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
				// Honor 'keep-alive' header
				HeaderElementIterator it = new BasicHeaderElementIterator(
						response.headerIterator(HTTP.CONN_KEEP_ALIVE));
				while (it.hasNext()) {
					HeaderElement he = it.nextElement();
					String param = he.getName();
					String value = he.getValue();
					if (value != null && param.equalsIgnoreCase("timeout")) {
						try {
							return Long.parseLong(value) * 1000;
						} catch (NumberFormatException ignore) {
						}
					}
				}
				return 30 * 1000;
			}
		};

		this.httpClient = HttpClients.custom().setConnectionManager(cm)
				// .setDefaultRequestConfig(globalConfig)
				.setKeepAliveStrategy(myStrategy).build();

	}

	/**
	 * http头信息的设置
	 *
	 * @param httpRequestBase
	 */
	private void config(HttpRequestBase httpRequestBase) {
		// httpRequestBase.setHeader(HTTP.CONTENT_TYPE, "application/json");
		// httpRequestBase.setHeader("User-Agent", "Mozilla/5.0");
		// httpRequestBase.setHeader("Accept",
		// "text/html,application/xhtml+xml,application/xml,application/json;q=0.9,*/*;q=0.8");
		// httpRequestBase.setHeader("Accept-Language",
		// "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");//"en-US,en;q=0.5");
		// httpRequestBase.setHeader("Accept-Charset",
		// "ISO-8859-1,utf-8,gbk,gb2312;q=0.7,*;q=0.7");
		// httpRequestBase.setHeader("connection", "Keep-Alive");
		// 配置请求的超时设置
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(connectionRequestTimeout)
				.setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).build();
		httpRequestBase.setConfig(requestConfig);
	}

}
