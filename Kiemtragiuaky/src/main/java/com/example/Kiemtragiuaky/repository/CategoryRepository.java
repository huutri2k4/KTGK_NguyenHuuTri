package com.example.Kiemtragiuaky.repository;

import com.example.Kiemtragiuaky.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // JpaRepository sẽ tự cung cấp phương thức findAll(), save(), delete()...
}