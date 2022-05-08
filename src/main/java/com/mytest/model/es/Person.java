package com.mytest.model.es;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/*@Data
@Document(indexName = "person")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder*/
public class Person implements Serializable {

    @Id
    private Integer id;

    //地址
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String address;

    //年龄
    @Field(type = FieldType.Integer)
    private Integer age;

    //姓名
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String name;

    //性别
    @Field(type = FieldType.Integer)
    private Integer sex;
}
