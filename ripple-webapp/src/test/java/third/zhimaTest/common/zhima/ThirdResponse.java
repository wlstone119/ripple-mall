package third.zhimaTest.common.zhima;

import java.io.Serializable;

public class ThirdResponse<T> implements Serializable {
    private static final long serialVersionUID = -3227768693076442960L;
    /** 调用成功标识 */
    private boolean success;

    /** 是否为内部错误:在真实调用之前的错误 */
    private boolean innerError;

    /** 调用文字消息 */
    private String message;

    /** 调用错误码 */
    private String code;

    /** 原始结果，可能是json，xml，输入流等等 */
    private Object original;

    /** 调用结果:一般是反序列化的结果 */
    private T data;

    public void setErrorCode(ErrorCode errorCode){
        this.code = errorCode.getCode();
        this.message = errorCode.getMsg();
    }

    public void setErrorCode(ErrorCode errorCode,String... params){
        this.code = errorCode.getCode();
        String msg = errorCode.getMsg();
        if(null != params
                && params.length > 0
                && errorCode.getMsg().contains("{}")){
            for (String s : params){
                msg = msg.replaceFirst("\\{\\}",s);
            }
            msg = msg.replaceAll("\\{\\}","");
        }
        this.message = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isInnerError() {
        return innerError;
    }

    public void setInnerError(boolean innerError) {
        this.innerError = innerError;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Object getOriginal() {
        return original;
    }

    public void setOriginal(Object original) {
        this.original = original;
    }
}
