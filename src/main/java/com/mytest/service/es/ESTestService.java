package com.mytest.service.es;

import com.mytest.model.es.Person;

import java.io.IOException;

public interface ESTestService {

    String syncUserInfoWithId(String req);

    String syncUserInfoByIds(String req);

    String batchPutDataToEs();

    String put(String key, String value);

    String get(String key);

    String updateData(String userId) throws IOException;

    String createUserWithId(String id);

    String createPerson(Person person);

    String createIndex(String name) throws IOException;
}
