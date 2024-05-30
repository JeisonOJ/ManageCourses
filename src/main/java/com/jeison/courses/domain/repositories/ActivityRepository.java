package com.jeison.courses.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeison.courses.domain.entities.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity,Long>{

}