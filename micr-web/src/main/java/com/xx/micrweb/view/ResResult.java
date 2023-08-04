package com.xx.micrweb.view;

import com.xa.common.enums.ResCode;

import java.util.List;

/*统一的应答结果*/
public class ResResult {
    private int code;
    private String msg;
//    访问系统token
    private String accessToken;
    /*存储单个数据*/
    private Object oneObj;
    /*存储集合数据*/
    private List list;
    private PageInfo page;
    /*请求成功的ResResult对象*/
    public static ResResult success(){
        ResResult resResult = new ResResult();
        resResult.setRcode(ResCode.SUCC);
        return resResult;
    }
    /*请求失败的ResResult对象*/
    public static ResResult fail(){
        ResResult resResult = new ResResult();
        resResult.setRcode(ResCode.UNKOWN);
        return resResult;
    }


    public void setRcode(ResCode resCode){
        this.code = resCode.getCode();
        this.msg = resCode.getText();
    }

    public PageInfo getPage() {
        return page;
    }

    public void setPage(PageInfo page) {
        this.page = page;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getOneObj() {
        return oneObj;
    }

    public void setOneObj(Object oneObj) {
        this.oneObj = oneObj;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public ResResult(int code, String msg, Object oneObj) {
        this.code = code;
        this.msg = msg;
        this.oneObj = oneObj;
    }

    public ResResult() {
    }

    @Override
    public String toString() {
        return "ResResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", oneObj=" + oneObj +
                '}';
    }
}
