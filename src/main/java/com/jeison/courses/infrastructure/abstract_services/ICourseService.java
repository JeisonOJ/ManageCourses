package com.jeison.courses.infrastructure.abstract_services;

import com.jeison.courses.api.dto.request.CourseReq;
import com.jeison.courses.api.dto.response.CourseResp;
import com.jeison.courses.api.dto.response.CourseRespWithLessons;

public interface ICourseService extends CrudService<CourseReq,CourseResp,Long>{

    public final String FIELD_BY_SORT = "courseName";
    public CourseRespWithLessons getAllCourseAndLessons(Long id);

}
