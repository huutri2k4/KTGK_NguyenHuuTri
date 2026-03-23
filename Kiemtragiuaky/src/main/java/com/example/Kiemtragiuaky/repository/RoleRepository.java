package com.example.Kiemtragiuaky.repository;

import com.example.Kiemtragiuaky.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
