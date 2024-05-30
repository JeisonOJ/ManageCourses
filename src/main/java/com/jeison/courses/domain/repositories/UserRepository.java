package com.jeison.courses.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeison.courses.domain.entities.User;
import com.jeison.courses.utils.enums.RoleUser;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

    public Optional<User> findByIdAndRoleUser(Long id, RoleUser roleUser);

}
