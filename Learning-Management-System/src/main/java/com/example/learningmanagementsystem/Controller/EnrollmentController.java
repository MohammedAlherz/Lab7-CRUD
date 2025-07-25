package com.example.learningmanagementsystem.Controller;

import com.example.learningmanagementsystem.Api.ApiResponse;
import com.example.learningmanagementsystem.Model.Course;
import com.example.learningmanagementsystem.Model.Enrollment;
import com.example.learningmanagementsystem.Service.EnrollmentService;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    // 1. Enroll a student in a course
    @PostMapping("/enroll/{studentId}/{courseId}")
    public ResponseEntity<?> enrollStudent(
            @PathVariable String studentId,
            @PathVariable String courseId) {

        boolean success = enrollmentService.enrollStudentInCourse(studentId, courseId);
        if (success) {
            return ResponseEntity.status(200).body(new ApiResponse("Student enrolled successfully in course."));
        } else {
            return ResponseEntity.status(400)
                    .body(new ApiResponse("Enrollment failed. Student or course not found, or already enrolled."));
        }
    }

    // 2. Drop a course
    @DeleteMapping("/drop/{studentId}/{courseId}")
    public ResponseEntity<?> dropCourse(
            @PathVariable String studentId,
            @PathVariable String courseId) {

        boolean success = enrollmentService.dropCourse(studentId, courseId);
        if (success) {
            return ResponseEntity.status(200).body(new ApiResponse("Course dropped successfully."));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Drop failed. Student or course not found, or not enrolled."));
        }
    }

    // 3. View all enrollments
    @GetMapping("/get-all")
    public ResponseEntity<List<Enrollment>> getAllEnrollments() {
        return ResponseEntity.ok(enrollmentService.getAllEnrollments());
    }

    // 4. Get courses for a specific student
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Course>> getCoursesForStudent(@PathVariable String studentId) {
        return ResponseEntity.ok(enrollmentService.getCoursesForStudent(studentId));
    }
}
