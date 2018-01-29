package com.stone.ripple.filter;

import org.apache.log4j.Logger;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.fastjson.JSON;

/**
 * @description
 * @author stone
 * @date 2017年12月5日
 */
public class DubboConsumerFilter implements Filter {

	private static Logger logger = Logger.getLogger(DubboConsumerFilter.class);

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		String clzName = invoker.getInterface().getSimpleName();
		String methodName = invocation.getMethodName();

		String clientIp = RpcContext.getContext().getRemoteHost();
		String params = jsonFilter(JSON.toJSONString(invocation.getArguments()));
		logger.info(String.format("外部调用开始：clientIp=>%s，%s=>%s，入参=>%s", clientIp, clzName, methodName, params));

		long startTime = System.currentTimeMillis();
		Result result = invoker.invoke(invocation);
		String rlt = jsonFilter(JSON.toJSONString(result));
		logger.info(String.format("外部调用结束：result=>%s, time:%s", rlt, System.currentTimeMillis() - startTime));

		return result;
	}

	private String jsonFilter(String params) {
		return params.replaceAll("\"id[c|C]ard[^,|}]*", "idcard:***")
				.replaceAll("\"bank[c|C]ard[^,|}]*", "bankcard:***").replaceAll("\"password[^,|}]*", "password:***")
				.replaceAll("\"image[^,|}]*", "image:***").replaceAll("\"delta[^,|}]*", "delta:***");
	}

}
