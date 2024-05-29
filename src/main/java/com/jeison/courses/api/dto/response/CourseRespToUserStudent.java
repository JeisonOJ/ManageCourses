package com.jeison.courses.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseRespToUserStudent {

    private Long id;
    private String courseName;
    private String description;

}
