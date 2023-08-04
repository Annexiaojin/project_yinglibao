package com.xa.common.util;

import java.util.regex.Pattern;

public class CommomUtil {
    /*处理pageNo*/
    public static int defaultPageNo(Integer pageNo){
        /*当前页不能为空  当前页的最小值也应该是1*/
        int pNo = pageNo;
        if (pageNo == null || pageNo < 1){
            pNo = 1;
        }
        return pNo;
    }

    /*处理pageSize*/
    public static int defaultPageSize(Integer pageSize){
        int pSize = pageSize;
        if (pageSize == null || pageSize < 1){
            pSize = 1;
        }
        return pSize;
    }
    /*手机号脱敏*/
    public static String tuoMinPhone(String phone){
        String result = "";
        if (phone != null && phone.trim().length() == 11){
            result = phone.substring(0,3) + "******" + phone.substring(9,11);
        }
        return result;
    }
    /*验证手机号格式*/
    public static  boolean checkPhone(String phone) {
        boolean flag = false;
        if (phone != null && phone.length() == 11){
            // 用来校验手机号的格式是否正确
            flag = Pattern.matches("^1[1-9]\\d{9}$", phone);
        }
        return flag;
    }
}
