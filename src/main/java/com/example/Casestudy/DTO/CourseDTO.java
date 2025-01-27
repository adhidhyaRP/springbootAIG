package com.example.Casestudy.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDTO {
    private Long courseId;
    private String courseName;

    public CourseDTO(Long courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }
}
