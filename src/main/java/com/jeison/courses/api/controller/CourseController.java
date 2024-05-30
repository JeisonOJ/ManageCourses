package com.jeison.courses.api.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jeison.courses.api.dto.request.CourseReq;
import com.jeison.courses.api.dto.response.CourseResp;
import com.jeison.courses.infrastructure.abstract_services.ICourseService;
import com.jeison.courses.utils.enums.SortType;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/courses")
public class CourseController {

  @Autowired
  private final ICourseService courseService;

  @GetMapping
  public ResponseEntity<Page<CourseResp>> getAll(
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "5") int size,
      @RequestHeader(required = false) SortType sortType) {
    if (Objects.isNull(sortType)) {
      sortType = SortType.NONE;
    }
    return ResponseEntity.ok(this.courseService.findAll(page - 1, size, sortType));
  }

  @GetMapping("/{id}")
  public ResponseEntity<CourseResp> getById(@PathVariable Long id) {
    return ResponseEntity.ok(courseService.findByIdWithDetails(id));
  }

  @PostMapping
  public ResponseEntity<CourseResp> createUser(@Validated @RequestBody CourseReq courseReq) {
    return ResponseEntity.ok(courseService.create(courseReq));
  }

  @PutMapping("{id}")
  public ResponseEntity<CourseResp> updateUser(@Validated @RequestBody CourseReq courseReq, @PathVariable Long id) {
    return ResponseEntity.ok(courseService.update(courseReq, id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    courseService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
