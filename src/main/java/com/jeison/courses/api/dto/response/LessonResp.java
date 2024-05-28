package com.jeison.courses.api.dto.response;

import java.util.List;

import com.jeison.courses.domain.entities.Activity;
import com.jeison.courses.domain.entities.Course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonResp {

    private Long id;
    private String lessonTitle;
    private String content;
    private List<Activity> activities;
    private Course course;

}
