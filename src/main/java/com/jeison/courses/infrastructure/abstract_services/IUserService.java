package com.jeison.courses.infrastructure.abstract_services;

import com.jeison.courses.api.dto.request.UserReq;
import com.jeison.courses.api.dto.response.UserResp;

public interface IUserService extends CrudService<UserReq,UserResp,Long>{

    public final String FIELD_BY_SORT = "userName";

}
