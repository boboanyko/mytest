package com.mytest.service.biz.impl;

import com.mytest.constants.BizConstants;
import com.mytest.dao.mapper.SchoolPOMapper;
import com.mytest.model.pos.School;
import com.mytest.model.vos.SchoolVO;
import com.mytest.rest.request.SchoolQueryRequest;
import com.mytest.rest.request.SchoolRequest;
import com.mytest.service.biz.SchoolService;
import com.mytest.util.OrikaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    SchoolPOMapper schoolPOMapper;

    @Override
    public int insertSchool(SchoolRequest request) {
        School record = School.builder()
                .schoolAddress(request.getSchoolAddress())
                .schoolLevel(request.getSchoolLevel())
                .schoolName(request.getSchoolName())
                .active(BizConstants.ACTIVE_TRUE)
                .createdBy(BizConstants.DEFAULT_USER)
                .updatedBy(BizConstants.DEFAULT_USER)
                .createdDate(new Date())
                .updatedDate(new Date())
                .build();

        return schoolPOMapper.insert(record);
    }

    @Override
    public int updateSchool(SchoolRequest request) {
        School schoolPO = schoolPOMapper.selectById(request.getSchoolId());
        schoolPO.setSchoolLevel(request.getSchoolLevel());
        schoolPO.setSchoolName(request.getSchoolName());
        schoolPO.setSchoolAddress(request.getSchoolAddress());
        schoolPO.setUpdatedDate(new Date());
        return schoolPOMapper.updateById(schoolPO);
    }

    @Override
    public SchoolVO getSchoolById(SchoolQueryRequest request) {
        School schoolPO = schoolPOMapper.selectById(request.getSchoolId());
        return OrikaUtils.convert(schoolPO, SchoolVO.class);
    }

    @Override
    public List<SchoolVO> getSchools(SchoolQueryRequest request) {
        schoolPOMapper.selectById(request.getSchoolId());

        return null;
    }
}
