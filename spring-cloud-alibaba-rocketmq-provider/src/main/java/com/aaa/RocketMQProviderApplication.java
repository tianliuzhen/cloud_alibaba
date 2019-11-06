package com.aaa;

import com.aaa.api.MySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
@SpringBootApplication
@EnableBinding({ MySource.class })
public class RocketMQProviderApplication {


    public static void main(String[] args) {
        SpringApplication.run(RocketMQProviderApplication.class, args);
    }


}
