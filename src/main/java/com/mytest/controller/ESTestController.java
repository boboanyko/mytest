package com.mytest.controller;


import com.mytest.service.es.ESTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/es")
public class ESTestController {

    @Autowired
    private ESTestService esTestService;


    @GetMapping("/put/{key}/{value}")
    public String esPut(@PathVariable String key, @PathVariable String value) {
        String put = esTestService.put(key, value);
        return put;
    }


    @GetMapping("/put1/{key}/{value}")
    public String esPut11(@PathVariable String key, @PathVariable String value) {
        String put = esTestService.syncUserInfoByIds(key);
        return put;
    }
}
