package com.wrd.consul;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RefreshScope动态刷新配置
@RefreshScope
@RestController
public class TestController {
    @Value("${config.info}")
    private String configInfo;

    @RequestMapping("getConfig")
    public String getConfig(){
        return  configInfo;
    }
}
