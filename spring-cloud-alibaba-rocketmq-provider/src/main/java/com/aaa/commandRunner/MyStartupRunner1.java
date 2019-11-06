package com.aaa.commandRunner;

import com.aaa.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/11/6
 */
@Component
@Order(value = 2)
@Configuration
public class MyStartupRunner1 implements CommandLineRunner {

    @Autowired
    private ProviderService providerService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunner 1");
    }
}
