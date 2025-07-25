package com.example.learningmanagementsystem.Service;

import com.example.learningmanagementsystem.Model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class StudentService {

    ArrayList<Student> students = new ArrayList<>();

    // Create a new student with auto-generated ID
    public void addStudent(Student student) {

        students.add(student);
    }

    // Get all students
    public ArrayList<Student> getAllStudents() {
        return students;
    }


    public boolean updateStudent(String id, Student updatedStudent) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                // Update student in the list
                students.set(students.indexOf(s), updatedStudent);
                return true;
            }
        }
        return false; // student not found
    }


    // Delete student by ID
    public boolean deleteStudent(String id) {
        return students.removeIf(s -> s.getId().equals(id));
    }



    public Student getStudentById(String studentId) {
        for (Student s : students) {
            if (s.getId().equals(studentId)) {
                return s; // Student found
            }
        }
        return null; // Student not found
    }

    //get by major
    public List<Student> getStudentsByMajor(String major) {
        List<Student> sameMajor = new ArrayList<>();
        for (Student s : students) {
            if (s.getMajor().equalsIgnoreCase(major)) {
                sameMajor.add(s);
            }
        }
        return sameMajor.isEmpty() ? null : sameMajor;
    }

    //get the highest GPA student
    public Student getHighestGPA() {
        if (students.isEmpty()) return null;

        Student highestGPAStudent = students.get(0);
        for (Student s : students) {
            if (s.getGpa() > highestGPAStudent.getGpa()) {
                highestGPAStudent = s;
            }
        }
        return highestGPAStudent;
    }
}
