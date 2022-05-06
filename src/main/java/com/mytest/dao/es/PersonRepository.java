package com.mytest.dao.es;

import com.mytest.model.es.Person;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;


@Component
public interface PersonRepository extends ElasticsearchRepository<Person,String> {
 
}