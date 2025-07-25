package com.example.learningmanagementsystem.Service;

import com.example.learningmanagementsystem.Model.Course;
import com.example.learningmanagementsystem.Model.Enrollment;
import com.example.learningmanagementsystem.Model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final StudentService studentService;
    private final CourseService courseService;

    private final List<Enrollment> enrollments = new ArrayList<>();

    // 1. Enroll a student in a course
    public boolean enrollStudentInCourse(String studentId, String courseId) {
        Student student = studentService.getStudentById(studentId);
        Course course = courseService.getCourseById(courseId);

        if (student == null || course == null) return false;

        // Check if already enrolled
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudent().getId().equals(studentId)
                    && enrollment.getCourse().getId().equals(courseId)) {
                return false; // already enrolled
            }
        }

        Enrollment newEnrollment = new Enrollment(student, course);
        enrollments.add(newEnrollment);
        return true;
    }

    // 2. Drop a course for a student
    public boolean dropCourse(String studentId, String courseId) {
        return enrollments.removeIf(enrollment ->
                enrollment.getStudent().getId().equals(studentId)
                        && enrollment.getCourse().getId().equals(courseId));
    }

    // 3. Get all enrollments
    public List<Enrollment> getAllEnrollments() {
        return enrollments;
    }

    // 4. Get courses for a student
    public List<Course> getCoursesForStudent(String studentId) {
        List<Course> courses = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudent().getId().equals(studentId)) {
                courses.add(enrollment.getCourse());
            }
        }
        return courses;
    }
}
