package com.example.Casestudy.controllers;
import com.example.Casestudy.DTO.CourseDTO;
import com.example.Casestudy.model.Course;
import com.example.Casestudy.model.Teacher;
import com.example.Casestudy.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;







    // Add a new teacher
    @PostMapping
    public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher teacher) {
        Teacher savedTeacher = teacherService.addTeacher(teacher);
        return ResponseEntity.ok(savedTeacher);
    }

    // Get courses by teacher ID
    @GetMapping("/{id}/courses")
    public ResponseEntity<List<CourseDTO>> getCoursesByTeacherId(@PathVariable long id) {
        return ResponseEntity.ok(teacherService.getCoursesByTeacherId(id));
    }

}