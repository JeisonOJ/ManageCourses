package com.jeison.courses.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jeison.courses.api.dto.request.CourseReq;
import com.jeison.courses.api.dto.response.CourseResp;
import com.jeison.courses.domain.entities.Course;
import com.jeison.courses.domain.entities.User;
import com.jeison.courses.domain.repositories.CourseRepository;
import com.jeison.courses.domain.repositories.UserRepository;
import com.jeison.courses.infrastructure.abstract_services.ICourseService;
import com.jeison.courses.infrastructure.helper.CourseHelper;
import com.jeison.courses.utils.enums.RoleUser;
import com.jeison.courses.utils.enums.SortType;
import com.jeison.courses.utils.exception.BadRequestException;
import com.jeison.courses.utils.message.ErrorMessage;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CourseService implements ICourseService {

    @Autowired
    private final CourseRepository courseRepository;
    @Autowired
    private final UserRepository userRepository;

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
        return courseRepository.findAll(pageable).map(course -> CourseHelper.courseToResp(course));
    }

    @Override
    public CourseResp findByIdWithDetails(Long id) {
        return CourseHelper.courseToResp(findById(id));
    }

    @Override
    public CourseResp create(CourseReq request) {
        User instructor = findInstructorById(request.getInstructorId());
        Course course = CourseHelper.reqToCourse(request);
        course.setInstructor(instructor);
        return CourseHelper.courseToResp(courseRepository.save(course));

    }

    @Override
    public CourseResp update(CourseReq request, Long id) {
        Course courseFound = findById(id);
        Course course = CourseHelper.reqToCourse(request);
        course.setId(id);
        course.setInstructor(courseFound.getInstructor());
        return CourseHelper.courseToResp(courseRepository.save(course));
    }

    @Override
    public void delete(Long id) {
        courseRepository.delete(findById(id));
    }

    private Course findById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("course")));
    }

    private User findInstructorById(Long id){
        return userRepository.findByIdAndRoleUser(id, RoleUser.INSTRUCTOR).orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("user",RoleUser.INSTRUCTOR.name())));
    }

}
