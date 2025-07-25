package com.example.learningmanagementsystem.Service;

import com.example.learningmanagementsystem.Model.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CourseService {

    ArrayList<Course> courses = new ArrayList<>();
    // Create a new course with auto-generated ID
    public void addCourse(Course course) {
        courses.add(course);
    }

    // Get all courses
    public ArrayList<Course> getAllCourses() {
        return courses;
    }

    // Update course by ID (cannot change ID)
    public boolean updateCourse(String id, Course updatedCourse) {
        for (Course c : courses) {
            if (c.getId().equals(id)) {
                courses.set(courses.indexOf(c), updatedCourse);
                return true;
            }
        }
        return false; // course not found
    }

    // Delete course by ID
    public boolean deleteCourse(String id) {
        return courses.removeIf(c -> c.getId().equals(id));
    }

    public Course getCourseById(String courseId) {
        for (Course c : courses) {
            if (c.getId().equals(courseId)) {
                return c; // Course found
            }
        }
        return null; // Course not found
    }

    //get courses by semester
    public ArrayList<Course> getCoursesBySemester(String semester) {
        ArrayList<Course> sameSemester = new ArrayList<>();
        for (Course c : courses) {
            if (c.getSemester().equalsIgnoreCase(semester)) {
                sameSemester.add(c);
            }
        }
        return sameSemester.isEmpty() ? null : sameSemester;
    }

    //check availability of course
    public boolean isCourseAvailable(String courseId) {
        for (Course c : courses) {
            if (c.getId().equals(courseId)) {
                return c.isAvailable(); // Check if course is available
            }
        }
        return false; // Course not found or not available
    }
    //change course availability
    public boolean changeAvailability(String courseId, boolean availability) {
        for (Course c : courses) {
            if (c.getId().equals(courseId)) {
                c.setAvailable(availability);
                return true; // Availability changed successfully
            }
        }
        return false; // Course not found
    }
}
