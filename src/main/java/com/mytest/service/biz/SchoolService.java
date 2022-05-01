package com.mytest.service.biz;

import com.mytest.model.vos.SchoolVO;
import com.mytest.rest.request.SchoolQueryRequest;
import com.mytest.rest.request.SchoolRequest;

import java.util.List;

public interface SchoolService {

    int insertSchool(SchoolRequest request);

    int updateSchool(SchoolRequest request);

    SchoolVO getSchoolById(SchoolQueryRequest request);

    List<SchoolVO> getSchools(SchoolQueryRequest request);
}
