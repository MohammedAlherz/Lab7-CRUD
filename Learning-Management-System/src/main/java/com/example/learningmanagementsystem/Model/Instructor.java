package com.example.learningmanagementsystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Instructor {

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

    @NotEmpty(message = "Qualification can not be empty")
    @Pattern(regexp = "Bachelor|Master|PhD", message = "Qualification must be either 'Bachelor', 'Master', or 'PhD'")
    private String qualification;

    @NotEmpty(message = "Department can not be empty")
    @Size(min = 2, max = 50, message = "Department must be between 2 and 50 characters")
    private String department;

    @NotEmpty(message = "Phone can not be empty")
    @Pattern(regexp = "^05\\d{8}$", message = "Phone number must start with '05' and be exactly 10 digits")
    private String phone;

    @PastOrPresent(message = "Hire date must be in the past or present")
    @NotNull(message = "Hire date can not be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate hireDate;

    @Positive(message = "Salary must be greater than 0")
    private double salary;

    private ArrayList<String> assignedCourseIds;


}
