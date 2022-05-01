package com.mytest.model.pos;

import lombok.Data;

import java.util.Date;

@Data
public class Teacher {
    private Integer id;

    private Integer schoolId;

    private String teacherName;

    private String teacherAddress;

    private String teacherLevel;

    private String teacherTopic;

    private String createdBy;

    private Date createdDate;

    private String updatedBy;

    private Date updatedDate;

    private String active;


}