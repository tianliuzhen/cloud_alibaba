package main.com.aaa.web;

import main.com.aaa.web.linuxIo.test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/10/22
 */
@RestController
public class web {

    @GetMapping(value = "12")
    public void  out(){
        test.execute("root","Haoxia123!","121.40.164.144",
                "tail -f -n 2 /opt/logs/wmlmicro-wechat-service-v1/spring.log.2019-10-21.0");
    }
}
