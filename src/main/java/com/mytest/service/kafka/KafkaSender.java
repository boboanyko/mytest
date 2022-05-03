package com.mytest.service.kafka;

import com.alibaba.fastjson.JSONObject;
import com.mytest.constants.BizConstants;
import com.mytest.model.kafka.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class KafkaSender {

/*    private final KafkaTemplate<String, String> kafkaTemplate;

    //构造器方式注入  kafkaTemplate
    public KafkaSender(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String msg) {
        Message message = new Message();

        message.setId(System.currentTimeMillis());
        message.setMsg(msg);
        message.setSendTime(new Date());
        log.info("【++++++++++++++++++ message ：{}】", JSONObject.toJSONString(message));
        //对 topic =  hello2 的发送消息
        kafkaTemplate.send("hello2",JSONObject.toJSONString(message));
    }*/


    @Autowired
    private KafkaTemplate<String,String> stringTemplate;


    public void sendStringMsg(String msg) {
        Message message = new Message();

        message.setId(System.currentTimeMillis());
        message.setMsg(msg);
        message.setSendTime(new Date());
        log.info("send message -- {} --", JSONObject.toJSONString(message));
//        stringTemplate.send(BizConstants.CAR_TYPE_TOPIC,JSONObject.toJSONString(message));
        stringTemplate.send(new ProducerRecord<>(BizConstants.CAR_TYPE_TOPIC,"car-type-key",JSONObject.toJSONString(message)));
    }

}