package com.stone.ripple.util.tool;

import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtil {

    private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);

    public static String createUrlWithoutParam(String fullUrl, String paramName, String paramValue) {
        String keyValue = paramName + "=" + paramValue;
        if (fullUrl.contains(keyValue + "&")) fullUrl = fullUrl.replace(keyValue + "&", "");
        else fullUrl = fullUrl.replace(keyValue, "");
        if ((fullUrl.endsWith("&")) || (fullUrl.endsWith("?"))) {
            fullUrl = fullUrl.substring(0, fullUrl.length() - 1);
        }
        return fullUrl;
    }

    public static Map<String, String> splitQueryString(String queryString) {
        Map paramMap = new LinkedHashMap();
        if (queryString == null) return paramMap;
        String[] params = queryString.split("&");
        if (params != null) {
            for (String param : params) {
                if (param != null) {
                    String[] kv = param.split("=");
                    if ((kv != null) && (kv.length > 0)) {
                        if (kv.length >= 2) paramMap.put(kv[0], kv[1]);
                        else paramMap.put(kv[0], "");
                    }
                }
            }
        }
        return paramMap;
    }

    public static String joinQueryString(Map<String, String> queryMap) {
        List queryList = new ArrayList();
        if ((queryMap == null) || (queryMap.size() < 1)) {
            return "";
        }
        for (String key : queryMap.keySet()) {
            queryList.add(key + "=" + ((String) queryMap.get(key)));
        }
        return StringUtils.join(queryList, "&");
    }

    public static String removeRequestUrlParameter(String requestUrlStr, String... parameterName) {
        if ((StringUtils.isBlank(requestUrlStr)) || (parameterName == null)) {
            return requestUrlStr;
        }
        try {
            URL requestUrl = new URL(requestUrlStr);
            Map params = splitQueryString(requestUrl.getQuery());
            for (String param : parameterName) {
                params.remove(param);
            }
            return builderRequestUriParams(requestUrlStr, params);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return requestUrlStr;
    }

    public static String addRequestUrlParameter(String requestUrlStr, String parameterName, String parametervalue) {
        if ((StringUtils.isBlank(requestUrlStr)) || (StringUtils.isBlank(parameterName))) {
            return requestUrlStr;
        }
        try {
            URL requestUrl = new URL(requestUrlStr);
            Map params = splitQueryString(requestUrl.getQuery());
            params.put(parameterName, parametervalue);
            return builderRequestUriParams(requestUrlStr, params);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return requestUrlStr;
    }

    public static String builderRequestUriParams(String requestUrlStr, Map<String, String> params) {
        try {
            if ((StringUtils.isBlank(requestUrlStr)) || (params == null)) {
                return requestUrlStr;
            }
            URL requestUrl = new URL(requestUrlStr);
            StringBuilder urlBuilder = new StringBuilder();

            List paramList = new ArrayList();
            for (String key : params.keySet()) {
                paramList.add(key + "=" + ((String) params.get(key)));
            }
            String queryString = StringUtils.join(paramList, "&");

            String port = "";
            if (requestUrl.getPort() > 0) {
                port = ":" + String.valueOf(requestUrl.getPort());
            }
            String ref = (StringUtils.isNotBlank(requestUrl.getRef())) ? "#" + requestUrl.getRef() : "";
            urlBuilder.append(requestUrl.getProtocol()).append("://");
            urlBuilder.append(requestUrl.getHost()).append(port).append(requestUrl.getPath());
            if (StringUtils.isNotBlank(queryString)) {
                urlBuilder.append("?").append(queryString).append(ref);
            }
            return urlBuilder.toString();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return requestUrlStr;
    }

    public static String encodeUrl(String urlStr) throws Exception {
        if (StringUtils.isBlank(urlStr)) return urlStr;
        URL url = new URL(urlStr);
        String queryString = url.getQuery();
        String path = url.getPath();
        String encodePath = URLEncoder.encode(path, "UTF-8").replace("%2F", "/");
        if (StringUtils.isNotBlank(queryString)) {
            Map<String, String> queries = splitQueryString(queryString);
            StringBuilder sb = new StringBuilder();
            for (Map.Entry entry : queries.entrySet()) {
                String value = (String) entry.getValue();
                String key = (String) entry.getKey();

                if (!(value.equals(URLDecoder.decode(value, "UTF-8")))) sb.append("&").append(key).append("=").append(value);
                else {
                    sb.append("&").append(key).append("=").append(URLEncoder.encode(value, "UTF-8"));
                }
            }

            String newQueryString = sb.substring(1);
            urlStr = urlStr.replace(queryString, newQueryString);
        }

        if ((StringUtils.isNotBlank(path)) && (StringUtils.isNotBlank(encodePath))) {
            urlStr = urlStr.replace(path, encodePath);
        }
        return urlStr;
    }

    public static String getRequestParameter(HttpServletRequest request, String name) {
        String param = "";
        if ((request == null) && (StringUtils.isBlank(name))) {
            return param;
        }
        try {
            if (StringUtils.equals(request.getMethod(), "POST")) {
                param = request.getParameter(name);
            } else {
                Map params = HttpUtil.splitQueryString(request.getQueryString());
                param = (String) params.get(name);
            }

            if (StringUtils.isNotBlank(param)) {
                param = URLDecoder.decode(param, "UTF-8");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return param;
    }

    public static String appendFullUrl(HttpServletRequest request) {
        StringBuffer fullUrl = new StringBuffer();
        fullUrl.append(request.getRequestURL());

        String qs = request.getQueryString();
        if (StringUtils.isNotBlank(qs)) {
            fullUrl.append("?").append(qs);
        }
        return fullUrl.toString();
    }

    public static String getDomainUrl(HttpServletRequest request) {
        String fullUrl = appendFullUrl(request);
        StringBuilder urlBuilder = new StringBuilder();

        try {
            URL url = new URL(fullUrl);
            urlBuilder.append(url.getProtocol()).append("://");

            String port = "";
            if (url.getPort() > 0) {
                port = ":" + String.valueOf(url.getPort());
            }

            urlBuilder.append(url.getHost()).append(port);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return urlBuilder.toString();
    }

    public static String getHost(String backUrl) {
        String host = "";
        try {
            int index = backUrl.indexOf(63);
            if (index > 0) {
                backUrl = backUrl.substring(0, index);
            }
            backUrl = URLDecoder.decode(backUrl, "UTF-8");
            URL url = new URL(backUrl);
            host = url.getHost();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return host;
    }

    public static String replaceSpecialCharacters(String url) {
        if (StringUtils.isBlank(url)) return url;
        return url.replace(" ", "%20").replace("|", "%7C").replace("{", "%7B").replace("}", "%7D");
    }

    /**
     * 获得全域名（不要从envutil获取）
     *
     * @param request
     * @return
     */
    public static String getFullDomainName(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName();
    }
}
