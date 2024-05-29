package com.jeison.courses.infrastructure.abstract_services;

import org.springframework.data.domain.Page;

import com.jeison.courses.utils.enums.SortType;

public interface CrudService<RQ, RS, ID> {

    public Page<RS> findAll(int page, int size, SortType sortType);

    public RS findByIdWithDetails(ID id);

    public RS create(RQ request);

    public RS update(RQ request, ID id);

    public void delete(ID id);
}
