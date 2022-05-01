package com.mytest.service.es.impl;

import com.alibaba.fastjson.JSONObject;
import com.mytest.config.elasticsearch.ESClient;
import com.mytest.dao.es.UserInfoRepository;
import com.mytest.model.UserInfoEsDto;
import com.mytest.service.es.ESTestService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ESTestServiceImpl implements ESTestService {

    @Autowired
    private ESClient esClient;


    @Resource
    private UserInfoRepository userInfoRepository;

    @Override
    public String syncUserInfoByIds(String req) {
        List<UserInfoEsDto> list = new ArrayList<>();
        UserInfoEsDto build = UserInfoEsDto.builder()
                .userId(1L)
                .content(req)
                .name("zhangsan")
                .publishTime(new Date())
                .build();
        UserInfoEsDto build1 = UserInfoEsDto.builder()
                .userId(2L)
                .content(req)
                .name("zhangsan2")
                .publishTime(new Date())
                .build();
        list.add(build);
        list.add(build1);


        if (list != null && !list.isEmpty()) {
            userInfoRepository.saveAll(list);
        }
        return "ok";
    }


    @Override
    public String put(String key, String value) {

        //获取esclient
        RestHighLevelClient restHighLevelClient = esClient.restHighLevelClient;
        SearchRequest searchRequest = new SearchRequest("userinfo");

        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchAllQuery());

        searchRequest.source(builder);

        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            return JSONObject.toJSONString(searchResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(JSONObject.toJSONString(searchResponse));
        return null;
    }
}
