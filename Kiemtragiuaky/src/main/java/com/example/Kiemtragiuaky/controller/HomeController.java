package com.example.Kiemtragiuaky.controller;

import com.example.Kiemtragiuaky.entity.Course;
import com.example.Kiemtragiuaky.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/home")
    public String home(Model model,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(required = false) String keyword) {
        // Phân trang: mỗi trang 5 học phần (Câu 1)
        Pageable pageable = PageRequest.of(page, 5);
        Page<Course> coursePage;

        // Xử lý tìm kiếm theo tên (Câu 8) [cite: 44]
        if (keyword != null && !keyword.isEmpty()) {
            coursePage = courseRepository.findByNameContaining(keyword, pageable);
        } else {
            coursePage = courseRepository.findAll(pageable);
        }

        model.addAttribute("courses", coursePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", coursePage.getTotalPages());
        model.addAttribute("keyword", keyword);

        return "home"; // Trả về file home.html trong templates
    }
}