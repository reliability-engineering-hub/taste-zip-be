package com.example.tastezip.repository;

import com.example.tastezip.model.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
}
