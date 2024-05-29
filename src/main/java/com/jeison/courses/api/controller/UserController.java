package com.jeison.courses.api.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeison.courses.api.dto.request.UserReq;
import com.jeison.courses.api.dto.response.UserResp;
import com.jeison.courses.infrastructure.abstract_services.IUserService;
import com.jeison.courses.utils.enums.SortType;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

  @Autowired
  private final IUserService userService;

  @GetMapping
  public ResponseEntity<Page<UserResp>> getAll(
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "5") int size,
      @RequestHeader(required = false) SortType sortType) {
    if (Objects.isNull(sortType)) {
      sortType = SortType.NONE;
    }
    return ResponseEntity.ok(this.userService.findAll(page - 1, size, sortType));
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserResp> getById(@PathVariable Long id) {
    return ResponseEntity.ok(userService.findByIdWithDetails(id));
  }

  @PostMapping
  public ResponseEntity<UserResp> createUser(@Validated @RequestBody UserReq userReq) {
    return ResponseEntity.ok(userService.create(userReq));
  }

  @PutMapping("{id}")
  public ResponseEntity<UserResp> updateUser(@Validated @RequestBody UserReq userReq, @PathVariable Long id) {
    return ResponseEntity.ok(userService.update(userReq, id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    userService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
