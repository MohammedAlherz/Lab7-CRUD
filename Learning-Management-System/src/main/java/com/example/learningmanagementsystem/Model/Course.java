package com.example.learningmanagementsystem.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Course {

    @NotEmpty(message = "Id can not be null")
    private String id;
    @NotEmpty(message = "Course name cannot be empty")
    @Size(min = 3, max = 50, message = "Course name must be between 3 and 50 characters")
    private String courseName;

    @NotEmpty(message = "Description cannot be empty")
    @Size(min = 10, max = 300, message = "Description must be between 10 and 300 characters")
    private String description;

    @NotEmpty(message = "Semester cannot be empty")
    @Pattern(regexp = "^(First|Second|Third|Fourth)$", message = "Semester must be one of: First, Second, Third, or Fourth")
    private String semester;

    @Min(value = 1, message = "Max capacity must be at least 1")
    private int maxCapacity;

    @Min(value = 0, message = "Current enrollment cannot be negative")
    private int currentEnrollment;

    private boolean isAvailable;

    private String instructorId;
}
