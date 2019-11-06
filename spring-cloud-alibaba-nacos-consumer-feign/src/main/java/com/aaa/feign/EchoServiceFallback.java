package com.aaa.feign;

import org.springframework.stereotype.Component;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/10/16
 */
@Component
public class EchoServiceFallback  implements EchoService {
    @Override
    public String echo(String message) {
        return "origin error!";
    }
}
