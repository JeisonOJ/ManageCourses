package com.jeison.courses.infrastructure.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jeison.courses.api.dto.request.UserReq;
import com.jeison.courses.api.dto.response.CourseRespToUserInstructor;
import com.jeison.courses.api.dto.response.CourseRespToUserStudent;
import com.jeison.courses.api.dto.response.EnrollmentRespToUserStudent;
import com.jeison.courses.api.dto.response.MessageRespToUser;
import com.jeison.courses.api.dto.response.SubmissionRespToUser;
import com.jeison.courses.api.dto.response.UserResp;
import com.jeison.courses.domain.entities.Course;
import com.jeison.courses.domain.entities.Enrollment;
import com.jeison.courses.domain.entities.Message;
import com.jeison.courses.domain.entities.Submission;
import com.jeison.courses.domain.entities.User;
import com.jeison.courses.domain.repositories.UserRepository;
import com.jeison.courses.infrastructure.abstract_services.IUserService;
import com.jeison.courses.utils.enums.RoleUser;
import com.jeison.courses.utils.enums.SortType;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    @Autowired
    private final UserRepository userRepository;

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
        return userRepository.findAll(pageable).map(this::userToUserResp);

    }

    @Override
    public UserResp findByIdWithDetails(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByIdWithDetails'");
    }

    @Override
    public UserResp create(UserReq request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public UserResp update(UserReq request, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    private UserResp userToUserResp(User user) {
        List<EnrollmentRespToUserStudent> enrollments = new ArrayList<>();
        List<CourseRespToUserInstructor> courses = new ArrayList<>();
        List<SubmissionRespToUser> submissions = user.getSubmissions().stream().map(this::submissionToResp).toList();
        List<MessageRespToUser> sentMessages = user.getSentMessages().stream().map(this::messageToResp).toList();
        List<MessageRespToUser> receivedMessages = user.getReceivedMessages().stream().map(this::messageToResp).toList();
        if (user.getRoleUser().equals(RoleUser.STUDENT)) {
            enrollments = user.getEnrollments().stream().map(this::enrollToResp)
                    .toList();
        }
        if (user.getRoleUser().equals(RoleUser.INSTRUCTOR)) {
            // enrollments = user.getEnrollments().stream().map(this::enrollToResp)
            //         .toList();
        }
        return UserResp.builder()
        .id(user.getId())
        .userName(user.getUserName())
        .email(user.getEmail())
        .roleUser(user.getRoleUser())
        .enrollments(enrollments)
        .courses(courses)
        .submissions(submissions)
        .receivedMessages(receivedMessages)
        .sentMessages(sentMessages)
        .build();
    }

    private EnrollmentRespToUserStudent enrollToResp(Enrollment enrollment) {
        return EnrollmentRespToUserStudent.builder()
                .id(enrollment.getId())
                .date(enrollment.getDate())
                .course(courseToRespStudent(enrollment
                        .getCourse()))
                .build();
    }

    private CourseRespToUserStudent courseToRespStudent(Course course) {
        return CourseRespToUserStudent.builder()
                .id(course.getId())
                .courseName(course.getCourseName())
                .description(course.getDescription())
                .build();
    }

    private SubmissionRespToUser submissionToResp(Submission submission){
        return SubmissionRespToUser.builder()
        .id(submission.getId())
        .content(submission.getContent())
        .grade(submission.getGrade())
        .submissionDate(submission.getSubmissionDate())
        .build();
    }

    private MessageRespToUser messageToResp(Message message){
        return MessageRespToUser.builder()
        .id(message.getId())
        .messageContent(message.getMessageContent())
        .sentDate(message.getSentDate())
        .build();
    }

}
