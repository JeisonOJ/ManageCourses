package com.jeison.courses.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeison.courses.domain.entities.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long>{

    public List<Message> findBySenderIdAndReceiverId(Long senderId, Long receiverId);

}
