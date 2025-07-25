package com.example.learningmanagementsystem.Controller;

import com.example.learningmanagementsystem.Api.ApiResponse;
import com.example.learningmanagementsystem.Model.Course;
import com.example.learningmanagementsystem.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllCourses() {
        return ResponseEntity.status(200).body(courseService.getAllCourses());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCourse(@Valid @RequestBody Course course, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(errorMessage);
        }
        courseService.addCourse(course);
        return ResponseEntity.status(200).body(new ApiResponse("Course added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable String id, @Valid @RequestBody Course course, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(errorMessage);
        }
        boolean updated = courseService.updateCourse(id, course);
        if (updated) {
            return ResponseEntity.status(200).body(new ApiResponse("Course updated successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Course not found"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable String id) {
        boolean deleted = courseService.deleteCourse(id);
        if (deleted) {
            return ResponseEntity.status(200).body(new ApiResponse("Course deleted successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Course ID not found"));
        }
    }
    // 1. Get courses by semester
    @GetMapping("/get-by-semester/{semester}")
    public ResponseEntity<?> getCoursesBySemester(@PathVariable String semester) {
        if (courseService.getCoursesBySemester(semester) != null) {
            return ResponseEntity.status(200).body(courseService.getCoursesBySemester(semester));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse("No courses found for the specified semester"));
        }
    }

    //2. change availability of a course
    @GetMapping("/change-availability/{courseId}/{available}")
    public ResponseEntity<?> checkCourseAvailability(@PathVariable String courseId,@PathVariable boolean available) {
        boolean isAvailable = courseService.changeAvailability(courseId, available);
        if (isAvailable) {
            return ResponseEntity.status(200).body(new ApiResponse("Course is available"));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse("Course not available"));
        }
    }

    //3. check availability of a course
    @GetMapping("/check-availability/{courseId}")
    public ResponseEntity<?> isCourseAvailable(@PathVariable String courseId) {
        boolean isAvailable = courseService.isCourseAvailable(courseId);
        if (isAvailable) {
            return ResponseEntity.status(200).body(new ApiResponse("Course is available"));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse("Course not available"));
        }
    }

}
