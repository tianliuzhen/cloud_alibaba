package com.aaa.web;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/9/30
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {


    /**
     * 注入配置文件上下文
     */
    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Value("${user.name}")
    private String name;
    /**
     从上下文中读取配置
     */
    @GetMapping(value = "/hi")
    public String sayHi() {
        System.out.println(name);
        return "Hello " + applicationContext.getEnvironment().getProperty("user.name");
    }
}
