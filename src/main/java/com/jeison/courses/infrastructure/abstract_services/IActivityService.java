package com.jeison.courses.infrastructure.abstract_services;

import com.jeison.courses.api.dto.request.ActivityReq;
import com.jeison.courses.api.dto.response.ActivityResp;
import com.jeison.courses.api.dto.response.ActivityRespWithSubmissions;

public interface IActivityService extends CrudService<ActivityReq,ActivityResp,Long>{

    public final String FIELD_BY_SORT = "activityTitle";
    public ActivityRespWithSubmissions getActivityWithSubmissions(Long id);

}
