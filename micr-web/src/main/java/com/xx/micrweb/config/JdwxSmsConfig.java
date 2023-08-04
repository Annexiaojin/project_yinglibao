package com.xx.micrweb.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
/*
* @Component
  @ConfigurationProperties这两个是用来获取yml中的数据值
  prefix：指定properties/yml的配置的前缀
* */
@Component
@ConfigurationProperties(prefix = "jdwx.sms")
public class JdwxSmsConfig {
    private String url;
    private String appKey;
    private String content;
    private String loginContent;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLoginContent() {
        return loginContent;
    }

    public void setLoginContent(String loginContent) {
        this.loginContent = loginContent;
    }
}
