package com.xa;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

public class FastJsonTest {
    @Test
    public void test(){
        Student student = new Student();
        School school = new School();
        school.setSchName("xian university");
        school.setSchAddr("xian yanta");
        student.setId(1);
        student.setName("思思");
        student.setAge(30);
        String json = JSONObject.toJSONString(student);
        System.out.println(json);
    }
    @Test
    public void test01(){
        String json = "{\"id\":1,\"name\":\"小霞\",\"age\":20,\"school\":{\"schName\":\"北大\",\"schAdd\":\"北京\"}}";
        JSONObject jsonObject = JSONObject.parseObject(json);
        System.out.println("jsonObject="+jsonObject);
        String name = jsonObject.getString("name");
        String schAdd = jsonObject.getJSONObject("school").getString("schAdd");
        System.out.println("name="+name);
        System.out.println("学校地址为："+schAdd);
    }
}
