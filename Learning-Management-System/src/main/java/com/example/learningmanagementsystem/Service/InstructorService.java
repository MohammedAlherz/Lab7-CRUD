package com.example.learningmanagementsystem.Service;

import com.example.learningmanagementsystem.Model.Course;
import com.example.learningmanagementsystem.Model.Instructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class InstructorService {

    ArrayList<Instructor> instructors = new ArrayList<>();
    private final CourseService courseService;
    // Create new instructor with auto-generated ID
    public void addInstructor(Instructor instructor) {
        instructors.add(instructor);
    }

    // Get all instructors
    public ArrayList<Instructor> getAllInstructors() {
        return instructors;
    }

    // Update instructor by ID (cannot change ID)
    public boolean updateInstructor(String id, Instructor updatedInstructor) {
        for (Instructor i : instructors) {
            if (i.getId().equals(id)) {
                instructors.set(instructors.indexOf(i), updatedInstructor);
                return true;
            }
        }
        return false; // instructor not found
    }

    // Delete instructor by ID
    public boolean deleteInstructor(String id) {
        return instructors.removeIf(i -> i.getId().equals(id));
    }

    public Instructor getInstructorById(String id) {
        for (Instructor i : instructors) {
            if (i.getId().equals(id)) {
                return i;
            }
        }
        return null;
    }
    public boolean assignCourseToInstructor(String instructorId, String courseId, CourseService courseService) {
        Instructor instructor = getInstructorById(instructorId);
        if (instructor == null) return false;

        Course course = courseService.getCourseById(courseId);
        if (course == null) return false;

        // Check if course is already assigned
        if (!course.getInstructorId().isEmpty()) return false;

        course.setInstructorId(instructorId);
        instructor.getAssignedCourseIds().add(courseId); // optional tracking
        return true;
    }

    //getInstructor same quilification
    public ArrayList<Instructor> getInstructorsByQualification(String qualification) {
        ArrayList<Instructor> qualifiedInstructors = new ArrayList<>();
        for (Instructor instructor : instructors) {
            if (instructor.getQualification().equalsIgnoreCase(qualification)) {
                qualifiedInstructors.add(instructor);
            }
        }
        return qualifiedInstructors;
    }

    //getInstructor courses
    public ArrayList<Course> getCoursesByInstructor(String instructorId) {
        ArrayList<Course> courses = new ArrayList<>();
        for (Course course : courseService.getAllCourses()) {
            if (course.getInstructorId().equals(instructorId)) {
                courses.add(course);
            }
        }
        return courses;
    }

    //getInstructor same department
    public ArrayList<Instructor> getInstructorsByDepartment(String department) {
        ArrayList<Instructor> departmentInstructors = new ArrayList<>();
        for (Instructor instructor : instructors) {
            if (instructor.getDepartment().equalsIgnoreCase(department)) {
                departmentInstructors.add(instructor);
            }
        }
        return departmentInstructors;
    }

}
