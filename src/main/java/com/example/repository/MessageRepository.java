package com.example.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{

    @Query("FROM Message WHERE message_id = :id")
    Message findByMessageId(@Param("id") Integer message_id);

    @Query("FROM Message WHERE posted_by = :id")
    List<Message> findAllByAccountId(@Param("id") Integer posted_by);

}
