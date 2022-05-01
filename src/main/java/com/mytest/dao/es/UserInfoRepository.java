package com.mytest.dao.es;

import com.mytest.model.UserInfoEsDto;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public interface UserInfoRepository extends ElasticsearchRepository<UserInfoEsDto,String> {
 
}