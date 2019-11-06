package com.aaa.web;

import com.aaa.api.MySink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.core.ParameterizedTypeReference;
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
@EnableBinding({ MySink.class })
public class ConsumerController {
    @Autowired
    private MySink mySink;


    @GetMapping("test")
    public void test() throws InterruptedException {
        while (true) {
            mySink.input5().poll(m -> {
                String payload = (String) m.getPayload();
                System.out.println("pull msg: " + payload);
            }, new ParameterizedTypeReference<String>() {
            });
            Thread.sleep(2_000);
        }
  }
}
