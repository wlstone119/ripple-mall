package third.zhimaTest.common.zhima;

public enum ErrorCode {
    _6000("6000","调用第三方成功"),
    _6001("6001","调用第三方失败"),
    _6002("6002","调用[{}]失败"),
    _6003("6003","[{}]结果解析失败"),
    _6004("6004","[{}]提示:{}"),
    _6005("6005","调用[{}]成功"),
    ;

    private String code;
    private String msg;

    ErrorCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
