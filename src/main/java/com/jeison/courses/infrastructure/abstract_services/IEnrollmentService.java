package com.jeison.courses.infrastructure.abstract_services;

import com.jeison.courses.api.dto.request.EnrollmentReq;
import com.jeison.courses.api.dto.response.EnrollmentResp;

public interface IEnrollmentService extends CrudService<EnrollmentReq,EnrollmentResp,Long>{

    public final String FIELD_BY_SORT = "date";

}
