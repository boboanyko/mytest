package com.mytest.controller;

import com.mytest.service.kafka.KafkaSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
@Slf4j
public class KafkaTestController {


    @Autowired
    private KafkaSender kafkaSender;


    @GetMapping("/send/msg/{msg}")
    public void sendMessage(@PathVariable("msg") String msg){
        kafkaSender.send(msg);
    }
}