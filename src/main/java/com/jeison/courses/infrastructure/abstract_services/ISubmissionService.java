package com.jeison.courses.infrastructure.abstract_services;

import com.jeison.courses.api.dto.request.SubmissionReq;
import com.jeison.courses.api.dto.response.SubmissionResp;

public interface ISubmissionService extends CrudService<SubmissionReq,SubmissionResp,Long>{

    public final String FIELD_BY_SORT = "grade";

}
