package com.example.Casestudy.services;

import com.example.Casestudy.model.Course;
import com.example.Casestudy.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    // Add a new course
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }
}
