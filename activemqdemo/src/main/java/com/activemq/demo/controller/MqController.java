package com.activemq.demo.controller;

import com.activemq.demo.producer.Producer;
import com.activemq.demo.producer.TopicProducer;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;
import javax.jms.JMSException;

@RestController
@RequestMapping("/mq")
public class MqController {


    private final Destination DEFAULT_QUEUE = new ActiveMQQueue("springboot.queue");

//    private final Destination TOPIC_NAME = new ActiveMQTopic("springboot.topic");
    @Autowired
    private Producer producer;
    @Autowired
    private TopicProducer topicProducer;

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

    /**
     * 主题订阅模式消息
     */
    @RequestMapping("/sendTopicMsg")
    public void sendTopicMsg(String msg) throws JMSException {
        topicProducer.producer(msg);
    }
}
