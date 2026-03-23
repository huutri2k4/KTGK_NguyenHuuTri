package com.example.Kiemtragiuaky.repository;

import com.example.Kiemtragiuaky.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByUsername(String username);
}