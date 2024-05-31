package com.jeison.courses.infrastructure.helper;

import com.jeison.courses.api.dto.request.SubmissionReq;
import com.jeison.courses.api.dto.response.SubmissionResp;
import com.jeison.courses.domain.entities.Submission;

public class SubmissionHelper {

    public static SubmissionResp submissionToResp(Submission submission){
        return SubmissionResp.builder()
        .id(submission.getId())
        .content(submission.getContent())
        .submissionDate(submission.getSubmissionDate())
        .grade(submission.getGrade())
        .build();
    }

    public static Submission reqToSubmission(SubmissionReq submissionReq){
        return Submission.builder()
        .grade(submissionReq.getGrade())
        .content(submissionReq.getContent())
        .build();
    }
}
