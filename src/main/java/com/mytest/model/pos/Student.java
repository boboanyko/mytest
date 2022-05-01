package com.mytest.model.pos;

import lombok.Data;

import java.util.Date;

@Data
public class Student {
    private Integer id;

    private Integer schoolId;

    private String studentName;

    private String studentAddress;

    private String headTeacher;

    private String grade;

    private String stuClass;

    private String createdBy;

    private Date createdDate;

    private String updatedBy;

    private Date updatedDate;

    private String active;

}