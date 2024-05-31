package com.jeison.courses.infrastructure.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jeison.courses.api.dto.request.UserReq;
import com.jeison.courses.api.dto.response.EnrollmentResp;
import com.jeison.courses.api.dto.response.UserResp;
import com.jeison.courses.api.dto.response.UserRespWithCourses;
import com.jeison.courses.domain.entities.User;
import com.jeison.courses.domain.repositories.EnrollmentRepository;
import com.jeison.courses.domain.repositories.UserRepository;
import com.jeison.courses.infrastructure.abstract_services.IUserService;
import com.jeison.courses.infrastructure.helper.EnrollmentHelper;
import com.jeison.courses.infrastructure.helper.UserHelper;
import com.jeison.courses.utils.enums.SortType;
import com.jeison.courses.utils.exception.BadRequestException;
import com.jeison.courses.utils.message.ErrorMessage;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final EnrollmentRepository enrollmentRepository;

    @Override
    public Page<UserResp> findAll(int page, int size, SortType sortType) {

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
        return userRepository.findAll(pageable).map(user -> UserHelper.userToResp(user));

    }

    @Override
    public UserResp findByIdWithDetails(Long id) {
        return UserHelper.userToResp(findById(id));
    }

    @Override
    public UserResp create(UserReq request) {
        return UserHelper.userToResp(userRepository.save(UserHelper.reqToUser(request)));
    }

    @Override
    public UserResp update(UserReq request, Long id) {
        findById(id);
        User user = UserHelper.reqToUser(request);
        user.setId(id);
        return UserHelper.userToResp(userRepository.save(user));
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(findById(id));
    }

    private User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("user")));
    }

    @Override
    public UserRespWithCourses getUsersWithCourses(Long id) {
        List<EnrollmentResp> enrollments = enrollmentRepository.findByStudentId(id).stream()
                .map(enrollment -> EnrollmentHelper.enrollmentToResp(enrollment)).toList();
        User student = findById(id);
        return UserRespWithCourses.builder()
        .id(student.getId())
        .userName(student.getUserName())
        .email(student.getEmail())
        .fullname(student.getFullName())
        .roleUser(student.getRoleUser())
        .courses(enrollments)
        .build();
         
    }

}
