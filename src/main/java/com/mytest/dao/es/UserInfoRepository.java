package com.mytest.dao.es;

import com.mytest.model.es.UserInfoEsDto;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;


//@Component
public interface UserInfoRepository extends ElasticsearchRepository<UserInfoEsDto,String> {
 
}