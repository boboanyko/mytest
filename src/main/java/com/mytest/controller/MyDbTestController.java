package com.mytest.controller;


import com.alibaba.fastjson.JSONObject;
import com.mytest.model.vos.SchoolVO;
import com.mytest.rest.request.SchoolQueryRequest;
import com.mytest.rest.request.SchoolRequest;
import com.mytest.service.biz.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mydb")
public class MyDbTestController {

    @Autowired
    private SchoolService schoolService;

    @PostMapping("/insert/school")
    public String insertSchool(@RequestBody SchoolRequest request) {
        int i = schoolService.insertSchool(request);
        return String.valueOf(i);
    }

    @PostMapping("/update/school")
    public String updateSchool(@RequestBody SchoolRequest request) {
        int i = schoolService.updateSchool(request);
        return String.valueOf(i);
    }

    @PostMapping("/query/school")
    public String querySchool(@RequestBody SchoolQueryRequest request) {
        SchoolVO vo = schoolService.getSchoolById(request);
        return JSONObject.toJSONString(vo);
    }
}
