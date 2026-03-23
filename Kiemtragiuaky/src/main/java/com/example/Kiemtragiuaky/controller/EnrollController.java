package com.example.Kiemtragiuaky.controller; // Thêm package này vào đầu file

import com.example.Kiemtragiuaky.entity.Course;
import com.example.Kiemtragiuaky.entity.Student;
import com.example.Kiemtragiuaky.repository.CourseRepository;
import com.example.Kiemtragiuaky.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Model phải là cái này, không phải ch.qos.logback
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class EnrollController {
    @Autowired private StudentRepository studentRepo;
    @Autowired private CourseRepository courseRepo;

    @GetMapping("/enroll/{id}")
    public String enroll(@PathVariable Long id, Principal principal) {
        if (principal == null) return "redirect:/login";

        // Tìm sinh viên và môn học
        Student student = studentRepo.findByUsername(principal.getName());
        Course course = courseRepo.findById(id).orElse(null);

        if (student != null && course != null) {
            // Thêm môn học vào Set courses của Student
            student.getCourses().add(course);
            studentRepo.save(student);
        }

        return "redirect:/my-courses";
    }

    @GetMapping("/my-courses")
    public String myCourses(Model model, Principal principal) {
        if (principal == null) return "redirect:/login";

        Student student = studentRepo.findByUsername(principal.getName());
        if (student != null) {
            model.addAttribute("myCourses", student.getCourses());
        }

        return "my-courses";
    }
}