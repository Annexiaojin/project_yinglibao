package com.bj.springboot.dataservice.mapper;

import com.bj.springboot.api.model.User;
import com.bj.springboot.api.pojo.UserAccountInfo;

public interface UserMapper {
    /*查询用户总数*/
    int queryCountUser();
    //通过手机号查询用户
    User queryByPhone(String phone);
    //添加记录  获取主键
    int insertReturnPrimaryKey(User user);
    //验证登录用户是否存在
    User userLogin(String phone,String pwd);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    int modifyName(String phone,String idCard,String name);
    UserAccountInfo queryUserAllInfo(Integer id);

}