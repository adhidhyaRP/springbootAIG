package com.example.Casestudy.controllers;

import com.example.Casestudy.DTO.StudentDTO;
import com.example.Casestudy.model.Student;
import com.example.Casestudy.model.Teacher;
import com.example.Casestudy.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable long id) {
        Optional<StudentDTO> studentDTO = studentService.getStudentById(id);
        return studentDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Add a single student
    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    // Add multiple students (Bulk Insert)
    @PostMapping("/bulk")
    public List<Student> addStudents(@RequestBody List<Student> students) {
        return studentService.addStudents(students);
    }

    // Delete a single student by ID
    @DeleteMapping("/{id}")
    public String deleteStudentById(@PathVariable Long id) {
        return studentService.deleteStudentById(id);
    }

    // Delete all students
    @DeleteMapping
    public String deleteAllStudents() {
        return studentService.deleteAllStudents();
    }


    // Update a single student by ID
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        return studentService.updateStudent(id, studentDetails);
    }

    // Update multiple students (Bulk Update)
    @PutMapping
    public List<Student> updateStudents(@RequestBody List<Student> students) {
        return studentService.updateStudents(students);
    }

    //fetch student and associated course by std_id
    @GetMapping("/{id}/course")
    public Student fetchStudentWithCourseById(@PathVariable("id") Long id) {
        return studentService.getStudentWithCourseById(id);
    }

    @GetMapping("/{id}/details")
    public Optional<Student> getStudentWithCourseAndTeacherById(@PathVariable Long id) {
        return studentService.getStudentWithCourseAndTeacherById(id);
    }

    @GetMapping("/{id}/teachers")
    public List<Teacher> getTeachersByStudentId(@PathVariable Long id) {
        return studentService.getTeachersForStudent(id);
    }
}
