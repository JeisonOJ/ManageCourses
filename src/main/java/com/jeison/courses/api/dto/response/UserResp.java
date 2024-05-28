package com.jeison.courses.api.dto.response;

import java.util.List;

import com.jeison.courses.utils.enums.RoleUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResp {

    private Long id;
    private String userName;
    private String email;
    private RoleUser roleUser;
    private List<CourseRespToUser> courses;
    private List<EnrollmentRespToUser> enrollments;
    private List<SubmissionRespToUser> submissions;
    private List<MessageRespToUser> messages;

}
