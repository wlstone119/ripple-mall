package com.stone.ripple.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.stone.ripple.facade.HelloFacade;
import com.weidai.clalarm.facade.api.DataAnalyseFacadeService;
import com.weidai.clalarm.facade.model.DataAnalyseBO;
import com.weidai.zm.norm.rpc.RpcResponse;

/**
 * @description
 * @author stone
 * @date 2017年12月15日
 */
public class HelloFacadeService {

    @Autowired
    private HelloFacade      helloFacade;

//    @Autowired
//    DataAnalyseFacadeService dataAnalyseFacadeService;

    public String test() {
        return helloFacade.hello();
    }

//    public RpcResponse<Void> test2() {
//        DataAnalyseBO data = new DataAnalyseBO();
//        data.setType("");
//        data.setData("[{'id':'123'}]");
//        return dataAnalyseFacadeService.execute(data);
//    }

}
