package com.example.logisticproject.repo;

import com.example.logisticproject.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
    @Query("select a from Attachment a where a.active = false")
    List<Attachment> findAllActiveFalse();


    @Query("select a from Attachment a where a.active = false and a.id = ?1")
    Optional<Attachment> findOneByIdWhichActiveFalse(String id);

    @Transactional
    @Modifying
    @Query("update Attachment a set a.active = ?2 where a.id = ?1 and a.active = false")
    int updateActiveByIdAndActiveFalse( UUID id, boolean active);
}