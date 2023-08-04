package com.xa.common.enums;

public enum ResCode {
    UNKOWN(0,"请稍后重试"),
    SUCC(200,"请求成功"),
    REQUEST_PARAM_ERR(201,"请求参数有误"),
    PHONE_TYPE(202,"手机号格式不正确"),
    PHONE_EXIST(203,"手机号已经注册"),
    SMS_PHONE_EXIST(204,"验证码可以继续使用"),
    SMS_CODE_ERR(205,"验证码错误"),
    REALNAME_REPEAT(205,"实名认证已经完成"),




    TOKEN_INVALID(300,"token无效"),
    ;

    ResCode(int c,String t){
        this.code = c;
        this.text = t;
    }

    private int code;
    private String text;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
