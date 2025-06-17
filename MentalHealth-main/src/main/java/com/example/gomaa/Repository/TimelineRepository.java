package com.example.gomaa.Repository;

import com.example.gomaa.entity.Timeline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimelineRepository extends JpaRepository<Timeline, Long> {
    // جلب السجلات الخاصة بمستخدم معين
    List<Timeline> findByUserId(Long userId);

    // (اختياري) حذف السجلات الخاصة بمستخدم معين
    void deleteByUserId(Long userId);
}