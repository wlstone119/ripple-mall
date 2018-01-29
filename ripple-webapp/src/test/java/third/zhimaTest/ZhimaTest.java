package third.zhimaTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.antgroup.zmxy.openplatform.api.DefaultZhimaClient;
import com.antgroup.zmxy.openplatform.api.ZhimaApiException;
import com.antgroup.zmxy.openplatform.api.request.ZhimaAuthInfoAuthorizeRequest;
import com.antgroup.zmxy.openplatform.api.request.ZhimaAuthInfoAuthqueryRequest;
import com.antgroup.zmxy.openplatform.api.request.ZhimaCreditScoreGetRequest;
import com.antgroup.zmxy.openplatform.api.request.ZhimaCreditWatchlistiiGetRequest;
import com.antgroup.zmxy.openplatform.api.response.ZhimaAuthInfoAuthqueryResponse;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCreditScoreGetResponse;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCreditWatchlistiiGetResponse;
import com.stone.ripple.dal.pojo.zhima.AppZhimaManageDo;
import com.stone.ripple.util.db.DbUtil;

import third.zhimaTest.common.zhima.ThirdResponse;

public class ZhimaTest {
    private static final String url = "https://zmopenapi.zmxy.com.cn/openapi.do";

    public static ThirdResponse<String> openId(String appKey) {
        ThirdResponse<String> response = new ThirdResponse<String>();
        ZhimaAuthInfoAuthqueryRequest request = new ZhimaAuthInfoAuthqueryRequest();
        request.setIdentityType("2");
        Map<String, String> identityParamMap = new HashMap<String, String>();
        identityParamMap.put("certNo", "******************");
        identityParamMap.put("certType", "IDENTITY_CARD");
        identityParamMap.put("name", "**");
        request.setIdentityParam(JSON.toJSONString(identityParamMap));
        try {
            ZhimaAuthInfoAuthqueryResponse zmResponse = getClient(appKey).execute(request);
            System.out.println(JSON.toJSONString(zmResponse));
            if (zmResponse.isSuccess()) {
                if (null != zmResponse.getAuthorized() && zmResponse.getAuthorized()) {
                    response.setSuccess(true);
                    response.setMessage("用户已授权");
                    response.setCode(zmResponse.getErrorCode());
                    response.setData(zmResponse.getOpenId());
                } else {
                    response.setInnerError(true);
                    response.setCode("5001");
                    response.setMessage("用户未授权");
                }
            } else {
                // 其他错误信息
                response.setCode(zmResponse.getErrorCode());
                response.setMessage(zmResponse.getErrorMessage());
            }
        } catch (ZhimaApiException e) {
            response.setMessage(e.getMessage());
            response.setInnerError(true);
        }
        return response;
    }

    /**
     * 获取芝麻授权链接
     * 
     * @param req ZhiMaLinkReq appKey 涉及回调 md5 人的唯一标识
     * @return
     */
    public static ThirdResponse<String> link(String appKey) {
        ThirdResponse<String> response = new ThirdResponse<String>();
        ZhimaAuthInfoAuthorizeRequest request = new ZhimaAuthInfoAuthorizeRequest();
        request.setIdentityType("2");
        request.setChannel("app");
        Map<String, Object> bizParams = new HashMap<String, Object>();
        bizParams.put("state", "");
        bizParams.put("auth_code", "M_H5");
        request.setBizParams(JSON.toJSONString(bizParams));
        Map<String, String> identityParamMap = new HashMap<String, String>();
        identityParamMap.put("certNo", "******************");
        identityParamMap.put("certType", "IDENTITY_CARD");
        identityParamMap.put("name", "***");
        request.setIdentityParam(JSON.toJSONString(identityParamMap));
        DefaultZhimaClient client = getClient(appKey);
        try {
            String url = client.generatePageRedirectInvokeUrl(request);
            response.setSuccess(true);
            response.setMessage("成功");
            response.setData(url);
            response.setOriginal(url);
        } catch (ZhimaApiException e) {
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /**
     * 芝麻分
     * 
     * @param req
     * @return
     */
    public static ThirdResponse<Integer> score(String appKey) {
        ThirdResponse<Integer> response = new ThirdResponse<Integer>();
        ZhimaCreditScoreGetRequest request = new ZhimaCreditScoreGetRequest();
        // 来源平台，默认为zmop
        request.setPlatform("zmop");
        // api=商户后台调用;apppc=商户pc端调用;app=商户移动app调用
        request.setChannel("apppc");
        // 业务流水号，对账使用
        request.setTransactionId("test3");
        // 芝麻分产品码
        request.setProductCode("w1010100100000000001");
        // 用户openid
        request.setOpenId(openId(appKey).getData());
        DefaultZhimaClient client = getClient(appKey);
        try {
            ZhimaCreditScoreGetResponse zmResponse = client.execute(request);
            if (null == zmResponse) {
                response.setMessage("调用接口失败:null");
                response.setInnerError(true);
            } else {
                // 无法评估
                if ("N/A".equals(zmResponse.getZmScore())) {
                    response.setMessage("无法评估芝麻分");
                    response.setData(-1);
                    response.setSuccess(true);
                    response.setOriginal(-1);
                } else {
                    response.setMessage("成功");
                    response.setOriginal(zmResponse.getZmScore());
                    response.setSuccess(true);
                    response.setData(Integer.parseInt(zmResponse.getZmScore()));
                }
            }
        } catch (ZhimaApiException e) {
            response.setInnerError(true);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /**
     * 行业关注名单
     * 
     * @param req
     * @return
     */
    public static ThirdResponse<ZhimaCreditWatchlistiiGetResponse> watchList(String appKey) {
        ThirdResponse<ZhimaCreditWatchlistiiGetResponse> response = new ThirdResponse<ZhimaCreditWatchlistiiGetResponse>();
        // 构建请求参数
        ZhimaCreditWatchlistiiGetRequest request = new ZhimaCreditWatchlistiiGetRequest();
        request.setPlatform("zmop");
        request.setChannel("apppc");
        request.setTransactionId("11111");
        request.setProductCode("w1010100100000000022");
        request.setOpenId(openId(appKey).getData());
        DefaultZhimaClient client = getClient(appKey);
        try {
            ZhimaCreditWatchlistiiGetResponse zmResponse = client.execute(request);
            response.setData(zmResponse);
            response.setOriginal(zmResponse);
            response.setSuccess(zmResponse.isSuccess());
            response.setMessage(zmResponse.getErrorMessage());
        } catch (ZhimaApiException e) {
            response.setMessage(e.getMessage());
            response.setInnerError(true);
        }
        return response;
    }

    private static DefaultZhimaClient getClient(String appKey) {
        String sql = "select *From app_zhima_manage where app_key= '" + appKey + "'";

        AppZhimaManageDo bean = new AppZhimaManageDo();
        DbUtil dbUtil = DbUtil.getInstance(); 
        try {
            List<AppZhimaManageDo> beanList = dbUtil.getObjectResultBySql(sql, null, new AppZhimaManageDo());
            if (beanList != null && beanList.size() > 0) {
                bean = beanList.get(0);
                System.out.println("appKey:" + bean.getAppKey() + "; appId: " + bean.getAppId() + " 应用名：" + bean.getAppName() + "; 环境：" + bean.getEnv());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DefaultZhimaClient(url, bean.getAppId(), bean.getPrivateZhimaKey(), bean.getPublicZhimaKey());
    }

    public static void main(String[] args) {
        System.out.println(Long.MIN_VALUE + "--" + Long.MAX_VALUE + "--" + Long.parseLong("111111111111111111"));
        System.out.println(JSON.toJSONString(link("f9930807305e503a76cc588d0ee42932")) + "\n");
        // System.out.println(JSON.toJSONString(score("37d4d13b8ccec48fe0dd3c53099e7bee")) + "\n");
        // System.out.println(JSON.toJSONString(score("03738b7660992b7ae0df860782cdbf48")) + "\n");
        // System.out.println(JSON.toJSONString(openId("6ee40a349a10c9bc6e4783de420ad3b8")) + "\n");
        // System.out.println(JSON.toJSONString(openId("1ef749f6d62fb5f3b33dd11f83da1a02")) + "\n");
        // System.out.println(JSON.toJSONString(openId("7d279822a2ed1846cfb3ccd5e94d5386")) + "\n");
        // System.out.println(JSON.toJSONString(openId("a9c5da27f74be234ba3b8465d1b372ce")) + "\n");
        // System.out.println(JSON.toJSONString(openId("047289c94bf70f02adeb0102f28a8293")) + "\n");
        // System.out.println(JSON.toJSONString(openId("a76248c6f57a2e86c78b52ac97098611")) + "\n");
        // System.out.println(JSON.toJSONString(openId("11d8bdca05fe26342186c5c94f434bc4")) + "\n");
        // System.out.println(JSON.toJSONString(watchList()));
        // System.out.println(JSON.toJSONString(link()));
        // System.out.println(JSON.toJSONString(score()));
    }
}
