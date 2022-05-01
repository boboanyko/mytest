package com.mytest.model.pos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

//@TableName("school")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class School {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String schoolName;

    private String schoolAddress;

    private String schoolLevel;

    private String createdBy;

    private Date createdDate;

    private String updatedBy;

    private Date updatedDate;

    private String active;


}