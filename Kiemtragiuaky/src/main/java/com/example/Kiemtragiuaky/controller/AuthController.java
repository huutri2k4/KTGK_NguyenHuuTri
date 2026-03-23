package com.example.Kiemtragiuaky.controller;

import com.example.Kiemtragiuaky.entity.Role;
import com.example.Kiemtragiuaky.entity.Student;
import com.example.Kiemtragiuaky.repository.RoleRepository;
import com.example.Kiemtragiuaky.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
public class AuthController {
    @Autowired private StudentRepository studentRepo;
    @Autowired private RoleRepository roleRepo;
    @Autowired private PasswordEncoder passwordEncoder;

    // Hiển thị form đăng ký
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("student", new Student());
        return "register";
    }

    // Xử lý lưu sinh viên mới (Câu 3)
    @PostMapping("/register")
    public String register(@ModelAttribute Student student) {
        // 1. Mã hóa mật khẩu cho bảo mật
        student.setPassword(passwordEncoder.encode(student.getPassword()));

        // 2. Tìm quyền STUDENT trong DB
        Role studentRole = roleRepo.findByName("STUDENT");

        // 3. Gán quyền mặc định (Câu 3 yêu cầu)
        if (studentRole != null) {
            student.setRoles(Collections.singleton(studentRole));
        }

        // 4. Lưu xuống database
        studentRepo.save(student);

        return "redirect:/login"; // Xong thì chuyển sang trang đăng nhập
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}