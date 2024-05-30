package com.jeison.courses.infrastructure.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jeison.courses.api.dto.request.CourseReq;
import com.jeison.courses.api.dto.response.CourseResp;
import com.jeison.courses.domain.entities.Course;
import com.jeison.courses.domain.repositories.CourseRepository;
import com.jeison.courses.infrastructure.abstract_services.ICourseService;
import com.jeison.courses.utils.enums.SortType;
import com.jeison.courses.utils.exception.BadRequestException;
import com.jeison.courses.utils.message.ErrorMessage;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CourseService implements ICourseService {

    private final CourseRepository courseRepository;

    @Override
    public Page<CourseResp> findAll(int page, int size, SortType sortType) {
        if (page < 0)
            page = 0;

        PageRequest pageRequest = null;

        switch (sortType) {
            case NONE -> pageRequest = PageRequest.of(page, size);
            case ASC -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
            default -> throw new IllegalArgumentException("No valid sort: " + sortType);
        }

        Pageable pageable = pageRequest;
        return courseRepository.findAll(pageable).map(this::courseToResp);
    }

    @Override
    public CourseResp findByIdWithDetails(Long id) {
        return courseToResp(findById(id));
    }

    @Override
    public CourseResp create(CourseReq request) {
        return courseToResp(courseRepository.save(reqToEntity(request)));

    }

    @Override
    public CourseResp update(CourseReq request, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    private CourseResp courseToResp(Course course) {
        return CourseResp.builder()
                .id(course.getId())
                .courseName(course.getCourseName())
                .description(course.getDescription())
                .build();
    }

    private Course reqToEntity(CourseReq courseReq) {
        return Course.builder()
                .courseName(courseReq.getCourseName())
                .description(courseReq.getDescription())
                
                .build();
    }

    private Course findById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("user")));
    }

}
