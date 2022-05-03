package com.mytest.service.kafka;

import com.alibaba.fastjson.JSONObject;
import com.mytest.constants.BizConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class KafkaReciver {


    @KafkaListener(id = "carTypeListener",containerFactory= "kafkaListenerContainerFactory" ,topics = {BizConstants.CAR_TYPE_TOPIC})
    public void listen(ConsumerRecord<?, ?> record, Acknowledgment acknowledgment) {

        log.info("KafkaReciver listen acknowledgment --> {}", acknowledgment);
        log.info("KafkaReciver listen record --> {}", record);
        log.info("KafkaReciver listen record key --> {}", record.key());

        Optional.ofNullable(record.value())
                .ifPresent(message -> {
                    log.info("【+++++++++++++++++ record = {} 】", record);
                    log.info("【+++++++++++++++++ message = {}】", message);
                });
        acknowledgment.acknowledge();
    }

}