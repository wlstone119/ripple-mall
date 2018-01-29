package third.zhimaTest.common.zhima;

import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;

public class BaseReq implements Serializable {
    private static final long serialVersionUID = 3616099659217671835L;

    /** 异步接口需要通过与appKey对应的openAppKey确定回调业务系统的配置 */
    @JSONField(serialize = false)
    private String openAppKey;

    /** 请求序列号:系统自动生成 */
    @JSONField(serialize = false)
    private Long serialNum;

    public String getOpenAppKey() {
        return openAppKey;
    }

    public void setOpenAppKey(String openAppKey) {
        this.openAppKey = openAppKey;
    }

    public Long getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(Long serialNum) {
        this.serialNum = serialNum;
    }
}
