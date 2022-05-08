package com.mytest.controller;


import com.mytest.model.es.Person;
import com.mytest.service.es.ESTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/es")
public class ESTestController {
/*
    @Autowired
    private ESTestService esTestService;

    @GetMapping("/create/index/{name}")
    public String createIndex(@PathVariable String name) throws IOException {
        String createIndexRet = esTestService.createIndex(name);
        return createIndexRet;
    }

    @PostMapping("/create/person")
    public String createIndex(@RequestBody Person person) throws IOException {
        String createIndexRet = esTestService.createPerson(person);
        return createIndexRet;
    }



    @GetMapping("/create/user/with/{id}")
    public String createUserWithId(@PathVariable String id) {
        String put = esTestService.syncUserInfoWithId(id);
        return put;
    }

    @GetMapping("/create2/user/with/{id}")
    public String create2UserWithId(@PathVariable String id) {
        String put = esTestService.createUserWithId(id);
        return put;
    }

    @GetMapping("/put/{key}/{value}")
    public String esPut(@PathVariable String key, @PathVariable String value) {
        String put = esTestService.put(key, value);
        return put;
    }


    @GetMapping("/batch/gen/users")
    public String batchGenUsers() {
        String put = esTestService.batchPutDataToEs();
        return put;
    }



    @GetMapping("/put1/{key}/{value}")
    public String esPut11(@PathVariable String key, @PathVariable String value) {
        String put = esTestService.syncUserInfoByIds(key);
        return put;
    }


    @GetMapping("/get/{keyword}")
    public String search(@PathVariable(required = false) String keyword,
                              @RequestParam(required = false) Long brandId,
                              @RequestParam(required = false) Long productCategoryId,
                              @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                              @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                              @RequestParam(required = false, defaultValue = "0") Integer sort) {
        String result = esTestService.get(keyword);
        return result;
    }

    @GetMapping("/update/{userId}")
    public String esPut11(@PathVariable String userId) throws IOException {
        String update = esTestService.updateData(userId);
        return update;
    }*/
}
