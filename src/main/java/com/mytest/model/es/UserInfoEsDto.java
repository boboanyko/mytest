package com.mytest.model.es;

import java.io.Serializable;
import java.util.Date;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
 
/**
 * @author nandao 2020/9/27
 */
/*@Data
@Document(indexName = "userinfo")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder*/
public class UserInfoEsDto implements Serializable {
 

    /**
     * es id
     */
    @Id
    private String id;
 
    //关联id
    @Field(type = FieldType.Long)
    private Long jid;
    /**
     * 用户ID
     */
    @Field(type = FieldType.Long)
    private Long userId;
 
    /**
     * 用户名称
     */
    @Field(type = FieldType.Text)
    private String name;
 
    /**
     * 内容
     */
    @ToString.Exclude
    @Field(type = FieldType.Text)
    private String content;
 
    /**
     * 时间
     */
    @Field(type = FieldType.Long)
    private Date publishTime;
 
 
}