package com.bj.springboot.dataservice.service;

import com.bj.springboot.api.model.FinanceAccount;
import com.bj.springboot.api.model.User;
import com.bj.springboot.api.pojo.UserAccountInfo;
import com.bj.springboot.api.service.UserService;
import com.bj.springboot.dataservice.mapper.FinanceAccountMapper;
import com.bj.springboot.dataservice.mapper.UserMapper;
import com.xa.common.util.CommomUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

@DubboService(interfaceClass = UserService.class,version = "1.0")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private FinanceAccountMapper financeAccountMapper;
    @Value("${ylb.config.passwordsalt}")
    private String passwordsalt;
    @Override
    public User queryByPhone(String phone) {
        User user = null;
        if (CommomUtil.checkPhone(phone)){
            user = userMapper.queryByPhone(phone);
        }
        return user;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public synchronized int userRegister(String phone, String pword) {
        int i = 0;
        if (CommomUtil.checkPhone(phone) &&(pword != null && pword.length()==32)){
            User queryUser = userMapper.queryByPhone(phone);
            if (queryUser == null){
                //给注册密码进行md5加密
                String newPassword = DigestUtils.md5Hex(pword + passwordsalt);
                //注册用户
                User user = new User();
                user.setPhone(phone);
                user.setLoginPassword(newPassword);
                user.setAddTime(new Date());
                i = userMapper.insertReturnPrimaryKey(user);
                //获取主键
                FinanceAccount account = new FinanceAccount();
                account.setUid(user.getId());
                account.setAvailableMoney(new BigDecimal("0"));
                financeAccountMapper.insertSelective(account);
                //成功
                i = 1; //表示成功
            }else{
                i = 2;
            }
        }
        return i;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public User userLogin(String phone, String pwd) {
        User user = null;
        if (CommomUtil.checkPhone(phone) &&(pwd != null && pwd.length() == 32)){
            user = userMapper.userLogin(phone,pwd);
            //更新用户最后的登陆时间
            if (user != null){
                user.setLastLoginTime(new Date());
                userMapper.updateByPrimaryKeySelective(user);
            }
        }
        return user;
    }

    @Override
    public int modifyName(String phone, String idCard, String name) {
         int i = userMapper.modifyName(phone,idCard,name);
         return i;
    }

    @Override
    public UserAccountInfo queryUserAllInfo(Integer id) {
        UserAccountInfo userAccountInfo = userMapper.queryUserAllInfo(id);
        return userAccountInfo;
    }
}
