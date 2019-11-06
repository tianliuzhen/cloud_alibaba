package com.aaa.web;

import com.aaa.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/11/6
 */
@RestController
public class SendController {
    @Autowired
    private ProviderService providerService;
    @GetMapping("/test")
    public void send(){
        providerService.send("测试 RocketMQ 1");
        providerService.send("测试 RocketMQ 2");
    }
}
