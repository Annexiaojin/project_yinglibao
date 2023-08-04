package com.example.axiosdemo.controller;

import com.example.axiosdemo.model.User;
import org.springframework.web.bind.annotation.*;

/*允许跨域访问*/
@CrossOrigin
@RestController
public class UserController {
    @GetMapping("/user")
    public User query(){
        System.out.println("服务器端获得请求");
        User user = new User(1, "xiaoxaio", 18, "女");
        return user;
    }
    @GetMapping("/user/param")
    public User queryUserByParam( Integer id,String name){
        User user = new User(id, name, 18, "女");
        return user;
    }
    @PostMapping("/user/add")
    public User queryUserByPost( Integer id,String name){
        System.out.println("===/user/add  post请求执行===");
        User user = new User(id, name, 18, "女");
        return user;
    }
    @PostMapping("/user/pod")
    public User queryByPost(@RequestBody User user){
        System.out.println("===/user/pod post请求执行===" +user);
        return user;
    }
}
