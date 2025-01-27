package com.example.Casestudy.repository;

import com.example.Casestudy.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM Course c WHERE c.teacher.teacherId = :teacherId")
    List<Course> findCoursesByTeacherId(@Param("teacherId") Long teacherId);





}