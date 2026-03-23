package com.example.Kiemtragiuaky.service;

import com.example.Kiemtragiuaky.entity.Student;
import com.example.Kiemtragiuaky.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentRepo.findByUsername(username);
        if (student == null) {
            throw new UsernameNotFoundException("Không tìm thấy sinh viên: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
                student.getUsername(),
                student.getPassword(),
                student.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName())) // Thêm ROLE_ vào đây
                        .collect(Collectors.toList())
        );
    }
}