package com.mytest.rest.request;

import lombok.Data;

@Data
public class SchoolQueryRequest {
    private Integer schoolId;
    private String schoolName;
    private String schoolAddress;
    private String schoolLevel;
}
