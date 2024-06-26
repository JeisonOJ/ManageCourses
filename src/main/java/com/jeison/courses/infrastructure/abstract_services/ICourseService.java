package com.jeison.courses.infrastructure.abstract_services;

import com.jeison.courses.api.dto.request.CourseReq;
import com.jeison.courses.api.dto.response.CourseResp;
import com.jeison.courses.api.dto.response.CourseRespWithLessons;
import com.jeison.courses.api.dto.response.CourseRespWithStudents;

public interface ICourseService extends CrudService<CourseReq,CourseResp,Long>{

    public final String FIELD_BY_SORT = "courseName";
    public CourseRespWithLessons getCourseWithLessons(Long id);
    public CourseRespWithStudents getCourseWithStudents(Long id);

}
