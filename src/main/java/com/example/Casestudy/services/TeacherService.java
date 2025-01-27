
package com.example.Casestudy.services;
import com.example.Casestudy.DTO.CourseDTO;
import com.example.Casestudy.model.Course;
import com.example.Casestudy.model.Teacher;
import com.example.Casestudy.repository.CourseRepository;
import com.example.Casestudy.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseRepository courseRepository;

    // Add a new teacher
    public Teacher addTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }



    // Get courses by teacher ID
    public List<CourseDTO> getCoursesByTeacherId(long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher Not Found"));

        return teacher.getCourses().stream()
                .map(course -> new CourseDTO(course.getCourseId(), course.getCourseName()))
                .collect(Collectors.toList());
    }



}
