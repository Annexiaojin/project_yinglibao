package com.xa;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

public class MyTest {
    @Test
    public void ObjectToJson(){
        Student student = new Student();
        student.setId(1);
        student.setName("潇潇");
        student.setAge(20);
        String json = JSONObject.toJSONString(student);
        System.out.println(json);

    }
    @Test
    public void JsonToObject(){
        String json = "{\"id\":1,\"name\":\"华华\",\"age\":30}";
        Student student = JSONObject.parseObject(json, Student.class);
        System.out.println(student);
    }
}
