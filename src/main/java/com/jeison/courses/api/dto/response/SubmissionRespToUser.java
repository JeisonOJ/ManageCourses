package com.jeison.courses.api.dto.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubmissionRespToUser {

    private Long id;
    private String content;
    private LocalDate submissionDate;
    private double grade;

}
