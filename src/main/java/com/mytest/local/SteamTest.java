package com.mytest.local;

import com.alibaba.fastjson.JSONObject;
import com.mytest.model.es.Person;

import java.util.Optional;

public class SteamTest {


    public static void main(String[] args) {
        Person person = new Person();
        person.setAge(20);
        Optional.ofNullable(person).ifPresent(p -> testSteam(person, p));

        System.out.println(JSONObject.toJSONString(person));
    }

    public static void testSteam(Person person1, Person person2) {

        person1.setAddress("上海");
        person2.setName("zhangsan");

    }
}
