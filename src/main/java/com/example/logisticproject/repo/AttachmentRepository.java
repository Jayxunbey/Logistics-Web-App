package com.example.logisticproject.repo;

import com.example.logisticproject.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttachmentRepository extends JpaRepository<Attachment, String> {
    @Query("select a from Attachment a where a.active = false")
    List<Attachment> findAllActiveFalse();
}