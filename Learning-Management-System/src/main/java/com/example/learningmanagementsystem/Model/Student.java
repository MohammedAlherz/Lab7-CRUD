package com.example.learningmanagementsystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Student {

    @NotEmpty(message = "Id can not be null")
    private String id;

    @NotEmpty(message = "First name can not be empty")
    @Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters")
    private String firstName;

    @NotEmpty(message = "Last name can not be empty")
    @Size(min = 2, max = 30, message = "Last name must be between 2 and 30 characters")
    private String lastName;

    @NotEmpty(message = "Email can not be empty")
    @Email(message = "Email must be valid")
    private String email;

    @NotEmpty(message = "Phone can not be empty")
    @Pattern(regexp = "^05\\d{8}$", message = "Phone number must start with '05' and be exactly 10 digits")
    private String phone;

    @NotEmpty(message = "Major can not be empty")
    @Size(min = 2, max = 50, message = "Major must be between 2 and 50 characters")
    private String major;

    @DecimalMin(value = "0.0", inclusive = true, message = "GPA must be at least 0.0")
    @DecimalMax(value = "5.0", inclusive = true, message = "GPA must be at most 5.0")
    private double gpa;

    @NotNull(message = "Enrollment date can not be null")
    @PastOrPresent(message = "Enrollment date must be in the past or present")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate enrollmentDate;


    private ArrayList<Course> courses;

}
