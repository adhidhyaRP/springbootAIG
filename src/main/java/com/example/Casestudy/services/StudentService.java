package com.example.Casestudy.services;

import com.example.Casestudy.DTO.StudentDTO;
import com.example.Casestudy.model.Student;
import com.example.Casestudy.model.Course;
import com.example.Casestudy.model.Teacher;
import com.example.Casestudy.repository.StudentRepository;
import com.example.Casestudy.repository.CourseRepository;
import com.example.Casestudy.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;  // Ensure the correct repository is autowired

    @Autowired
    private TeacherRepository teacherRepository;  // Ensure the correct repository is autowired


    // Get all students
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(student -> new StudentDTO(student.getStdId(), student.getStdName()))
                .collect(Collectors.toList());
    }



    public Optional<StudentDTO> getStudentById(long id) {
        return studentRepository.findById(id)
                .map(student -> new StudentDTO(student.getStdId(), student.getStdName()));
    }





    // Add a single student
    public Student addStudent(Student student) {

        if (!courseRepository.existsById(student.getCourse().getCourseId())) {
            throw new RuntimeException("Course with ID " + student.getCourse().getCourseId() + " not found.");
        }


        if (!teacherRepository.existsById(student.getTeacher().getTeacherId())) {
            throw new RuntimeException("Teacher with ID " + student.getTeacher().getTeacherId() + " not found.");
        }

        return studentRepository.save(student);
    }

    // Add multiple students (Bulk Insert)
    public List<Student> addStudents(List<Student> students) {
        for (Student student : students) {
            if (!courseRepository.existsById(student.getCourse().getCourseId())) {
                throw new RuntimeException("Course with ID " + student.getCourse().getCourseId() + " not found.");
            }
            if (!teacherRepository.existsById(student.getTeacher().getTeacherId())) {
                throw new RuntimeException("Teacher with ID " + student.getTeacher().getTeacherId() + " not found.");
            }
        }
        return studentRepository.saveAll(students);
    }

    // Delete a single student by ID
    public String deleteStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            studentRepository.deleteById(id);
            return "Student with ID " + id + " deleted successfully.";
        } else {
            return "Student with ID " + id + " not found.";
        }
    }

    // Delete all students
    public String deleteAllStudents() {
        studentRepository.deleteAll();
        return "All students deleted successfully.";
    }

    // Update a single student
    public Student updateStudent(Long id, Student studentDetails) {
        Optional<Student> studentOptional = studentRepository.findById(id);

        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            student.setStdName(studentDetails.getStdName());
            student.setCourse(studentDetails.getCourse());
            student.setTeacher(studentDetails.getTeacher());
            return studentRepository.save(student);
        } else {
            throw new RuntimeException("Student with ID " + id + " not found.");
        }
    }

    // Update multiple students
    public List<Student> updateStudents(List<Student> students) {
        return studentRepository.saveAll(students);
    }

    //fetch student and course by std_id
    public Student getStudentWithCourseById(Long stdId) {
        return studentRepository.fetchStudentWithCourseById(stdId);
    }

    public Optional<Student> getStudentWithCourseAndTeacherById(Long stdId) {
        return Optional.ofNullable(studentRepository.findStudentWithCourseAndTeacherById(stdId));
    }

    public List<Teacher> getTeachersForStudent(Long studentId) {
        return studentRepository.findTeachersByStudentId(studentId);
    }
}
