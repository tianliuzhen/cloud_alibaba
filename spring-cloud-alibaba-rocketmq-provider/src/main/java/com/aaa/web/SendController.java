package com.aaa.web;

import com.aaa.api.MySource;
import com.aaa.service.Foo;
import com.aaa.service.ProviderService;
import com.aaa.service.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * 自定义  默认的 output 测试
     */
    @GetMapping("/test")
    public void send(){
        providerService.send("测试 RocketMQ 1");
        providerService.send("测试 RocketMQ 2");
    }


    private final String bindingName="output1";

    @Autowired
    private SenderService senderService;

    @Autowired
    private MySource mySource;

    /**
     * 自定义 自官方文档改编
     *  发送单个字符串、
     *  带标签的字符串发送、
     *  带标签的对象发送、
     * @throws Exception
     */
    @GetMapping("/test2")
    public void send2(@RequestParam(defaultValue = "output1",required = false) String output) throws Exception {

        if ("output1".equals(output)) {
            int count = 5;
            for (int index = 1; index <= count; index++) {
                String msgContent = "msg-" + index;
                if (index % 3 == 0) {
                    //发送单个字符串
                    senderService.send(msgContent);
                }
                else if (index % 3 == 1) {
                    //带标签的字符串发送
                    senderService.sendWithTags(msgContent, "tagStr");
                }
                else {
                    //带标签的对象发送
                    senderService.sendObject(new Foo(index, "foo"), "tagObj");
                }
            }
        }
        else if ("output3".equals(output)) {
            mySource.output3().send(MessageBuilder.withPayload(output).build());
            int count = 20;
            for (int index = 1; index <= count; index++) {
                String msgContent = "pullMsg-" + index;
                mySource.output3()
                        .send(MessageBuilder.withPayload(msgContent).build());
            }
        }

    }

    /**
     * 待确定
     * @throws Exception
     */
    @GetMapping("/test3")
    public void send3() throws Exception {
        // COMMIT_MESSAGE message
        senderService.sendTransactionalMsg("transactional-msg1", 1);
        // ROLLBACK_MESSAGE message
        senderService.sendTransactionalMsg("transactional-msg2", 2);
        // ROLLBACK_MESSAGE message
        senderService.sendTransactionalMsg("transactional-msg3", 3);
        // COMMIT_MESSAGE message
        senderService.sendTransactionalMsg("transactional-msg4", 4);
    }
}
