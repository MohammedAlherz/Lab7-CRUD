package com.example.learningmanagementsystem.Controller;

import com.example.learningmanagementsystem.Api.ApiResponse;
import com.example.learningmanagementsystem.Model.Instructor;
import com.example.learningmanagementsystem.Service.CourseService;
import com.example.learningmanagementsystem.Service.InstructorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/instructors")
@RequiredArgsConstructor
public class InstructorController {

    private final InstructorService instructorService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllInstructors() {
        return ResponseEntity.status(200).body(instructorService.getAllInstructors());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addInstructor(@Valid @RequestBody Instructor instructor, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(errorMessage);
        }
        instructorService.addInstructor(instructor);
        return ResponseEntity.status(200).body(new ApiResponse("Instructor added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateInstructor(@PathVariable String id, @Valid @RequestBody Instructor instructor, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(errorMessage);
        }
        boolean updated = instructorService.updateInstructor(id, instructor);
        if (updated) {
            return ResponseEntity.status(200).body(new ApiResponse("Instructor updated successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Instructor not found"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteInstructor(@PathVariable String id) {
        boolean deleted = instructorService.deleteInstructor(id);
        if (deleted) {
            return ResponseEntity.status(200).body(new ApiResponse("Instructor deleted successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Instructor ID not found"));
        }
    }

    private final CourseService courseService;
    // 1. Assign a course to an instructor
    @PutMapping("/assign/{instructorId}/{courseId}")
    public ResponseEntity<?> assignCourseToInstructor(
            @PathVariable String instructorId,
            @PathVariable String courseId) {

        boolean assigned = instructorService.assignCourseToInstructor(instructorId, courseId, courseService);

        if (assigned) {
            return ResponseEntity.status(200).body(new ApiResponse("Course assigned to instructor successfully"));
        } else {
            return ResponseEntity.badRequest().body(new ApiResponse("Failed to assign course to instructor. Check IDs or if course is already assigned."));
        }
    }

    //2. Get instructors by qualification
    @GetMapping("/get-instructors-by-qualification/{qualification}")
    public ResponseEntity<?> getInstructorsByQualification(@PathVariable String qualification) {
        var instructors = instructorService.getInstructorsByQualification(qualification);
        if (instructors != null && !instructors.isEmpty()) {
            return ResponseEntity.status(200).body(instructors);
        } else {
            return ResponseEntity.status(404).body(new ApiResponse("No instructors found with the specified qualification"));
        }
    }

    //3. Get courses by instructor ID
    @GetMapping("/get-courses-by-instructor/{instructorId}")
    public ResponseEntity<?> getCoursesByInstructor(@PathVariable String instructorId) {
        var courses = instructorService.getCoursesByInstructor(instructorId);
        if (courses != null && !courses.isEmpty()) {
            return ResponseEntity.status(200).body(courses);
        } else {
            return ResponseEntity.status(404).body(new ApiResponse("No courses found for the specified instructor"));
        }
    }

    //4. get instructor same department
    @GetMapping("/get-instructors-by-department/{department}")
    public ResponseEntity<?> getInstructorsByDepartment(@PathVariable String department) {
        var instructors = instructorService.getInstructorsByDepartment(department);
        if (instructors != null && !instructors.isEmpty()) {
            return ResponseEntity.status(200).body(instructors);
        } else {
            return ResponseEntity.status(404).body(new ApiResponse("No instructors found in the specified department"));
        }
    }
}
