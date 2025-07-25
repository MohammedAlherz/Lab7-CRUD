package com.example.learningmanagementsystem.Controller;

import com.example.learningmanagementsystem.Api.ApiResponse;
import com.example.learningmanagementsystem.Model.Student;
import com.example.learningmanagementsystem.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllStudents() {
        return ResponseEntity.status(200).body(studentService.getAllStudents());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addStudent(@Valid @RequestBody Student student, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(errorMessage);
        }
        studentService.addStudent(student);
        return ResponseEntity.status(200).body(new ApiResponse("Student added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable String id, @Valid @RequestBody Student student, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(errorMessage);
        }
        boolean updated = studentService.updateStudent(id, student);
        if (updated) {
            return ResponseEntity.status(200).body(new ApiResponse("Student updated successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Student not found or ID change not allowed"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable String id) {
        boolean deleted = studentService.deleteStudent(id);
        if (deleted) {
            return ResponseEntity.status(200).body(new ApiResponse("Student deleted successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Student ID not found"));
        }
    }

    //1. get student by major
    @GetMapping("/get-by-major/{major}")
    public ResponseEntity<?> getStudentsByMajor(@PathVariable String major) {
        if (studentService.getStudentsByMajor(major) != null) {
            return ResponseEntity.status(200).body(studentService.getStudentsByMajor(major));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse("No students found for the specified major"));
        }
    }

    //2. get highest GPA student
    @GetMapping("/get-highest-gpa")
    public ResponseEntity<?> getHighestGpaStudent() {
        Student student = studentService.getHighestGPA();
        if (student != null) {
            return ResponseEntity.status(200).body(student);
        } else {
            return ResponseEntity.status(404).body(new ApiResponse("No students found"));
        }
    }
}
