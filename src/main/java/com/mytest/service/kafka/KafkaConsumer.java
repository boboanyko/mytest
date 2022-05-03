package com.mytest.service.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;


import java.util.Optional;

/**
 * @author ww
 * @date 2020/9/29 上午10:43
 */
@Component
@Slf4j
public class KafkaConsumer {

    /*@KafkaListener(containerFactory = "manualListenerContainerFactory" ,topics = com.mytest.service.kafka.KafkaProducer.TOPIC_TEST, groupId = com.mytest.service.kafka.KafkaProducer.TOPIC_GROUP1)
    public void topic_test(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        log.info("topic_test -- start ---");
        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            Object msg = message.get();
            log.info("消费者里面的group1接收到了消息，主题是:{},消息是:{}", topic,msg);
            ack.acknowledge();
        }
    }

    @KafkaListener(containerFactory = "manualImmediateListenerContainerFactory" ,topics = com.mytest.service.kafka.KafkaProducer.TOPIC_TEST, groupId = com.mytest.service.kafka.KafkaProducer.TOPIC_GROUP2)
    public void topic_test1(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        log.info("topic_test1 -- start ---");

        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            Object msg = message.get();
            log.info("消费者里面的group2接收到了消息，主题是:{},消息是:{}", topic,msg);
            ack.acknowledge();
        }
    }*/
}