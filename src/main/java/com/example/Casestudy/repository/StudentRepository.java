package com.example.Casestudy.repository;

import com.example.Casestudy.model.Student;
import com.example.Casestudy.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // fetch student and associated course by std_id
    @Query("SELECT s FROM Student s JOIN FETCH s.course WHERE s.stdId = :stdId")
    Student fetchStudentWithCourseById(@Param("stdId") Long stdId);

    @Query("SELECT s FROM Student s JOIN FETCH s.course c JOIN FETCH c.teacher t WHERE s.stdId = :stdId")
    Student findStudentWithCourseAndTeacherById(@Param("stdId") Long stdId);


    @Query("SELECT t FROM Teacher t JOIN t.students s WHERE s.stdId = :studentId")
    List<Teacher> findTeachersByStudentId(@Param("studentId") Long studentId);
}
