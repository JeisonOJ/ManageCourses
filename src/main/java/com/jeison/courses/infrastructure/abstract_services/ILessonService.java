package com.jeison.courses.infrastructure.abstract_services;

import com.jeison.courses.api.dto.request.LessonReq;
import com.jeison.courses.api.dto.response.LessonResp;
import com.jeison.courses.api.dto.response.LessonRespWithActivities;

public interface ILessonService extends CrudService<LessonReq,LessonResp,Long>{

    public final String FIELD_BY_SORT = "lessonTitle";
    public LessonRespWithActivities getLessonWithActivities(Long id);
}
