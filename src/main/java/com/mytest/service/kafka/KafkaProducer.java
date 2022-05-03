package com.mytest.service.kafka;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @author ww
 * @date 2020/9/29 上午10:43
 */
@Component
@Slf4j
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, String> stringTemplate;

    //自定义topic
    public static final String TOPIC_TEST = "mytopic";

    //分组1
    public static final String TOPIC_GROUP1 = "manu_group1";

    //分组2
    public static final String TOPIC_GROUP2 = "manu_group2";

    public void send(Object obj) {
/*        String message = JSONObject.toJSONString(obj);
        log.info("准备发送消息为：{}", message);
        //发送消息
        ListenableFuture<SendResult<String, String>> future = stringTemplate.send(TOPIC_TEST, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String,String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                //发送失败的处理
                log.info(TOPIC_TEST + " - 生产者 发送消息失败：" + throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String,String> stringObjectSendResult) {
                //成功的处理
                log.info(TOPIC_TEST + " - 生产者 发送消息成功：" + stringObjectSendResult.toString());
            }
        });*/


    }
}