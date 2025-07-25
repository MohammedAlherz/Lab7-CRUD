package com.example.learningmanagementsystem.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Enrollment {
    private Student student;
    private Course course;
}
