package com.crosslywere.tmp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crosslywere.tmp.entity.Classroom;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, String> {
}
