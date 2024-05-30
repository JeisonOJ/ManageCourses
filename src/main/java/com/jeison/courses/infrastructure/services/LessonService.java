package com.jeison.courses.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jeison.courses.api.dto.request.LessonReq;
import com.jeison.courses.api.dto.response.LessonResp;
import com.jeison.courses.domain.entities.Course;
import com.jeison.courses.domain.entities.Lesson;
import com.jeison.courses.domain.repositories.CourseRepository;
import com.jeison.courses.domain.repositories.LessonRepository;
import com.jeison.courses.infrastructure.abstract_services.ILessonService;
import com.jeison.courses.infrastructure.helper.LessonHelper;
import com.jeison.courses.utils.enums.SortType;
import com.jeison.courses.utils.exception.BadRequestException;
import com.jeison.courses.utils.message.ErrorMessage;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LessonService implements ILessonService {

    @Autowired
    private final LessonRepository lessonRepository;
    @Autowired
    private final CourseRepository courseRepository;

    @Override
    public Page<LessonResp> findAll(int page, int size, SortType sortType) {
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
        return lessonRepository.findAll(pageable).map(lesson -> LessonHelper.lessonToResp(lesson));
    }

    @Override
    public LessonResp findByIdWithDetails(Long id) {
        return LessonHelper.lessonToResp(findById(id));
    }

    @Override
    public LessonResp create(LessonReq request) {
        Course course = courseRepository.findById(request.getCourseId()).orElseThrow(()-> new BadRequestException(ErrorMessage.idNotFound("course")));
        Lesson lesson = LessonHelper.reqToLesson(request);
        lesson.setCourse(course);
        return LessonHelper.lessonToResp(lessonRepository.save(lesson));
    }

    @Override
    public LessonResp update(LessonReq request, Long id) {
        Lesson lessonFound =  findById(id);
        Lesson lesson = LessonHelper.reqToLesson(request);
        lesson.setId(id);
        lesson.setCourse(lessonFound.getCourse());
        return LessonHelper.lessonToResp(lessonRepository.save(lesson));
    }

    @Override
    public void delete(Long id) {
        lessonRepository.delete(findById(id));
    }

    private Lesson findById(Long id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("lesson")));
    }

}
