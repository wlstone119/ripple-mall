package basics;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.stone.ripple.util.tool.HttpClientUtil;

public class BaiduTest {

	private final static String host = "https://aip.baidubce.com/rpc/2.0/solution/v1/unit_utterance?";

	public static String post(String token, String sceneId, String sessionId, String query) {
		Map<String, Object> urlParams = new HashMap<>();
		urlParams.put("access_token", token);
		Map<String, Object> bodyParams = new HashMap<>();
		bodyParams.put("scene_id", sceneId);
		bodyParams.put("session_id", sessionId);
		bodyParams.put("query", query);
		String result = HttpClientUtil.post(host + "access_token=" + token, new JSONObject(bodyParams));

		return result;
	}

	public static void main(String[] args) {

		String token = "";
		String sceneId = "";
		String sessionId = "";
		String query = "";

		System.out.println(post(token, sceneId, sessionId, query));

	}

}
