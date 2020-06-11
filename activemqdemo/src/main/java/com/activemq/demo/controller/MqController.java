package com.activemq.demo.controller;

import com.activemq.demo.producer.Producer;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;

@RestController
@RequestMapping("/mq")
public class MqController {

    @Autowired
    private Producer producer;
    private final Destination DEFAULT_QUEUE = new ActiveMQQueue("springboot.queue");

    /**
     * 普通的消息
     * @param msg
     */
    @RequestMapping("/sendMsg1")
    public void sendMsg1(String msg){

        producer.send(DEFAULT_QUEUE,msg);
    }
    /**
     * 延时消息
     */
    @RequestMapping("/sendDeleyMsg")
    public void sendDeleyMsg(String msg){
        producer.delaySend(DEFAULT_QUEUE,msg,10*1000L);
    }
}
