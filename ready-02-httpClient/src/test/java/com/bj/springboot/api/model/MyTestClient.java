package com.bj.springboot.api.model;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyTestClient {
    @Test
    public void testGet(){
        //发起get请求
        String url="https://restapi.amap.com/v3/ip?key=0113a13c88697dcea6a445584d535837&ip=60.25.188.64";
        //创建HttpClient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //创建HttpGet对象
        HttpGet httpGet = new HttpGet(url);
        //执行请求，使用client对象的方法，执行请求后获取返回结果
        //CloseableHttpResponse是返回结果，相当于HttpServletResponse
        try{
            CloseableHttpResponse response = client.execute(httpGet);
            //从response获取应答的结果
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                //获取数据
                //InputStream content = response.getEntity().getContent();
                String json = EntityUtils.toString(response.getEntity());
                System.out.println(json);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭资源
            try {
                client.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    @Test
    public void testPost(){
        //创建HttpClient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //创建HttpPost对象
        String url = "https://restapi.amap.com/v3/ip";
        HttpPost httpPost = new HttpPost(url);
        //准备post请求的参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("key","0113a13c88697dcea6a445584d535837"));
       // params.add(new BasicNameValuePair("ip","60.25.188.64")); //天津
        params.add(new BasicNameValuePair("ip","124.89.117.2"));
        //设置httpPost使用参数
       try {
           httpPost.setEntity(new UrlEncodedFormEntity(params));
           //执行请求
           CloseableHttpResponse res = client.execute(httpPost);
           if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
               String json = EntityUtils.toString(res.getEntity());
               System.out.println(json);
           }
       }catch (Exception e){
           e.printStackTrace();
       }finally {
           try {
               client.close();
           } catch (IOException e) {
               throw new RuntimeException(e);
           }
       }


    }
}
