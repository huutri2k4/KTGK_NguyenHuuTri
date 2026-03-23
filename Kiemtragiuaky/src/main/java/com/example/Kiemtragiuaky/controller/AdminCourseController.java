package com.example.Kiemtragiuaky.controller;

import com.example.Kiemtragiuaky.entity.Course;
import com.example.Kiemtragiuaky.repository.CategoryRepository;
import com.example.Kiemtragiuaky.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/courses") // Câu 4: Đường dẫn bắt đầu bằng /admin
public class AdminCourseController {
    @Autowired private CourseRepository courseRepo;
    @Autowired private CategoryRepository categoryRepo;

    // Hiển thị danh sách để quản lý
    @GetMapping
    public String list(Model model) {
        model.addAttribute("courses", courseRepo.findAll());
        return "admin/course-list";
    }

    // Trang thêm mới học phần (Câu 2.1)
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("categories", categoryRepo.findAll());
        return "admin/course-add";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Course course) {
        courseRepo.save(course);
        return "redirect:/admin/courses"; // Sau khi lưu, nó sẽ quay về trang danh sách
    }

    // Trang cập nhật học phần (Câu 2.2)
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Course course = courseRepo.findById(id).orElseThrow();
        model.addAttribute("course", course);
        model.addAttribute("categories", categoryRepo.findAll());
        return "admin/course-edit";
    }

    // Xóa học phần (Câu 2.3)
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        courseRepo.deleteById(id);
        return "redirect:/admin/courses";
    }
}