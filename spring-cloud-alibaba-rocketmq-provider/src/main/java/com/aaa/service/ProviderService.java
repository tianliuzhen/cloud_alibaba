package com.aaa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@EnableBinding({Source.class})
public class ProviderService {
    @Autowired
    private MessageChannel output;


    public void send(String message) {
        output.send(MessageBuilder.withPayload(message).build());
    }

}
