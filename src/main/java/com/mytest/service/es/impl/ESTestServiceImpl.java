package com.mytest.service.es.impl;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytest.config.elasticsearch.ESClient;
import com.mytest.dao.es.PersonRepository;
import com.mytest.dao.es.UserInfoRepository;
import com.mytest.model.es.UserInfoEsDto;
import com.mytest.model.es.Person;
import com.mytest.service.es.ESTestService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ESTestServiceImpl implements ESTestService {

    /*@Autowired
    private ESClient esClient;


    @Resource
    private UserInfoRepository userInfoRepository;

    @Resource
    private PersonRepository personRepository;


    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public String syncUserInfoWithId(String req) {
        log.info("start --- ");
        if (StringUtils.isBlank(req)) {
            return "fail";
        }

        Long startTime = System.currentTimeMillis();
        long userId = Long.parseLong(req);
        UserInfoEsDto userInfoEsDto = genUserList(userId);

        //赋值
        Map<String,Object> jsonMap = new HashMap<>();
        ObjectMapper oMapper = new ObjectMapper();
        jsonMap = oMapper.convertValue(userInfoEsDto, Map.class);

        IndexRequest request = new IndexRequest("userinfo").id(req).source(jsonMap);
        //相应
        //同步执行的写法(当以下列方式执行IndexRequest时，客户端在继续执行代码之前，会等待返回索引响应:)
        // 同步调用可能会在高级REST客户端中解析REST响应失败、请求超时或类似服务器没有响应的情况下抛出IOException
        //  在服务器返回4xx或5xx错误代码的情况下，高级客户端会尝试解析响应主体错误详细信息，然后抛出一个通用的弹性响应异常，并将原始响应异常作为抑制异常添加到其中。
        //IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);

        //异步执行(我们也可以用异步方式执行IndexRequest，以便客户端可以直接返回。用户需要通过向异步索引方法传递请求和侦听器来指定如何处理响应或潜在故障:)
        ActionListener<IndexResponse> listener = new ActionListener<IndexResponse>() {
            @Override
            public void onResponse(IndexResponse indexResponse) {
                log.info("ActionListener IndexResponse --> {}",JSONObject.toJSONString(indexResponse));
            }
            @Override
            public void onFailure(Exception e) {
                log.error("ActionListener Exception -->",e);
            }
        };
        esClient.restHighLevelClient.indexAsync(request,RequestOptions.DEFAULT,listener);// listener是执行完成时要使用的侦听器

//        返回的IndexResponse对象允许检索关于已执行操作的信息
        //String index = indexResponse.getIndex();
        //String id = indexResponse.getId();
        //System.out.println("index: "+index+"  "+"id:  "+id)


        log.info("cost time -- > {}ms", (System.currentTimeMillis() - startTime));
        return "ok";
    }






    @Override
    public String syncUserInfoByIds(String req) {
        log.info("start --- ");
        if (StringUtils.isBlank(req)) {
            return "fail";
        }

        Long startTime = System.currentTimeMillis();
        List<UserInfoEsDto> list = new ArrayList<>();
        list.add(genUserList(Long.parseLong(req)));
        if (list != null && !list.isEmpty()) {
            userInfoRepository.saveAll(list);
        }

        log.info("cost time -- > {}s", (System.currentTimeMillis() - startTime)/1000);
        return "ok";
    }

    @Override
    public String batchPutDataToEs() {
        log.info("start --- ");
        Long startTime = System.currentTimeMillis();
        List<UserInfoEsDto> list = new ArrayList<>();
        for (long index = 1000; index < 10000; index++) {
            list.add(genUserList(index));
        }

        if (list != null && !list.isEmpty()) {
            userInfoRepository.saveAll(list);
        }

        log.info("cost time -- > {}s", (System.currentTimeMillis() - startTime)/1000);
        return "ok";
    }

    // 理论上是需要插入数据库成功后，返回ID了再来设置userInfo得id
    private UserInfoEsDto genUserList(Long index) {
        UserInfoEsDto build = UserInfoEsDto.builder()
                .userId(index)
                .content(UUID.randomUUID().toString())
                .name("zhangsan" + index)
                .publishTime(new Date())
                .build();

        return build;
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



    @Override
    public String get(String keyword) {

        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //分页
//        nativeSearchQueryBuilder.withPageable(pageable);
        //过滤
        *//*if (brandId != null || productCategoryId != null) {
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if (brandId != null) {
                boolQueryBuilder.must(QueryBuilders.termQuery("brandId", brandId));
            }
            if (productCategoryId != null) {
                boolQueryBuilder.must(QueryBuilders.termQuery("productCategoryId", productCategoryId));
            }
            nativeSearchQueryBuilder.withFilter(boolQueryBuilder);
        }*//*
        //搜索
        if (StringUtils.isEmpty(keyword)) {
            nativeSearchQueryBuilder.withQuery(QueryBuilders.matchAllQuery());
        } else {
            List<FunctionScoreQueryBuilder.FilterFunctionBuilder> filterFunctionBuilders = new ArrayList<>();
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("name", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(10)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("content", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(5)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("keywords", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(2)));
            FunctionScoreQueryBuilder.FilterFunctionBuilder[] builders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[filterFunctionBuilders.size()];
            filterFunctionBuilders.toArray(builders);

            FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builders)
                    .scoreMode(FunctionScoreQuery.ScoreMode.SUM)
                    .setMinScore(2);
            nativeSearchQueryBuilder.withQuery(functionScoreQueryBuilder);
        }
        //排序
        *//*if(sort==1){
            //按新品从新到旧
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("id").order(SortOrder.DESC));
        }else if(sort==2){
            //按销量从高到低
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("sale").order(SortOrder.DESC));
        }else if(sort==3){
            //按价格从低到高
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.ASC));
        }else if(sort==4){
            //按价格从高到低
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));
        }else{
            //按相关度
            nativeSearchQueryBuilder.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));
        }*//*
        nativeSearchQueryBuilder.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));
        NativeSearchQuery searchQuery = nativeSearchQueryBuilder.build();
        log.info("DSL:{}", searchQuery.getQuery().toString());
        SearchHits<UserInfoEsDto> searchHits = elasticsearchRestTemplate.search(searchQuery,UserInfoEsDto.class);
        if(searchHits.getTotalHits()<=0){
            return null;
        }
        List<UserInfoEsDto> searchProductList = searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
        return JSONObject.toJSONString(searchProductList);
    }

    @Override
    public String updateData(String userId) throws IOException {
        Map<String, Object> updateMap = new HashMap<>(5);
        updateMap.put("name", "黑骑五户");
        // 封装Script中的code 数据格式:ctx._source.goodsName=params.goodsName;
        StringBuilder sb = new StringBuilder();
        updateMap.keySet().stream().forEach(p -> {
            sb.append("ctx._source.").append(p).append("=params.").append(p).append(";");
        });
        log.info("update map --> {}",sb.toString());


        UpdateByQueryRequest updateByQueryRequest = new UpdateByQueryRequest("userinfo");
        updateByQueryRequest.setRefresh(true);
        // 设置查询条件
        updateByQueryRequest.setQuery(new TermQueryBuilder("userId",userId));
        //设置版本冲突时继续
        updateByQueryRequest.setConflicts("proceed");
        Script script = new Script(ScriptType.INLINE, "painless", sb.toString(), updateMap);
        updateByQueryRequest.setScript(script);

        try {
            BulkByScrollResponse bulkByScrollResponse = esClient.restHighLevelClient.updateByQuery(updateByQueryRequest, RequestOptions.DEFAULT);
            log.info("查询更新索引:[{}]操作共耗时:[{}],共修改文档数:[{}]",
                    "indexName", bulkByScrollResponse.getTook(), bulkByScrollResponse.getUpdated());
        } catch (IOException e) {
            log.error("查询更新索引:[{}]操作出现异常:{}", "indexName", e);
        }

 *//*       Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("name", "黑骑五户");
        UpdateByQueryRequest request = new UpdateByQueryRequest("userinfo");
        request.setIndicesOptions(IndicesOptions.STRICT_EXPAND_OPEN);
        request.setQuery(new TermQueryBuilder("userId",userId));

        esClient.restHighLevelClient.updateByQuery(request,RequestOptions.DEFAULT);*//*


        return null;
    }

    @Override
    public String createUserWithId(String id) {
        log.info("start --- ");
        if (StringUtils.isBlank(id)) {
            return "fail";
        }

        Long startTime = System.currentTimeMillis();
        long userId = Long.parseLong(id);
        UserInfoEsDto userInfoEsDto = genUserList(userId);

        UserInfoEsDto save = userInfoRepository.save(userInfoEsDto);
        log.info("createUserWithId save result --> {}",save);
        return JSONObject.toJSONString(save);
    }

    @Override
    public String createPerson(Person person) {
        log.info("create person -- {} --",JSONObject.toJSONString(person));
        Long startTime = System.currentTimeMillis();

        Person save = personRepository.save(person);
        log.info("createPerson save result --> {}",save);
        log.info("createPerson cost time --> {}", System.currentTimeMillis() - startTime);
        return JSONObject.toJSONString(save);
    }

    @Override
    public String createIndex(String name) throws IOException {


//            GetIndexRequest request = new GetIndexRequest(name);
//            esClient.restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        // 创建一个默认的index
//        CreateIndexRequest request = new CreateIndexRequest();
//        request.set
//        esClient.restHighLevelClient.indices().create(request);
        // 设置索引type
        XContentBuilder content = XContentFactory.jsonBuilder()
                .startObject()// {
                    .startObject("properties")// "properties" : {
                        .startObject("name")        // "name": {
                            .field("type", "text")// "type" : "text",
                            .field("analyzer", "ik_max_word")// "analyzer" : "ik_max_word"
                        .endObject()    // }
                        .startObject("age")
                            .field("type", "integer")
                        .endObject()
                        .startObject("sex")
                            .field("type", "integer")
                        .endObject()
                        .startObject("address")        // "address": {
                            .field("type", "text")// "type" : "text",
                            .field("analyzer", "ik_max_word")// "analyzer" : "ik_max_word"
                        .endObject()
                    .endObject() // }
                .endObject();// }

        CreateIndexRequest request = new CreateIndexRequest(name);
        request.mapping(content);
        CreateIndexResponse createIndexResponse = esClient.restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);

        boolean acknowledged = createIndexResponse.isAcknowledged();
        System.out.println("创建索引操作结果:" + acknowledged);


        return null;
    }*/
}
