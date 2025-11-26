package com.example.webpage_student.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "student_marks")
public class StudentMarks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String studentName;

    private Integer subject1_30;
    private Integer subject1_70;
    private Integer subject1_total;

    private Integer subject2_30;
    private Integer subject2_70;
    private Integer subject2_total;

    private Integer subject3_30;
    private Integer subject3_70;
    private Integer subject3_total;

    private Integer subject4_30;
    private Integer subject4_70;
    private Integer subject4_total;

    private Double cgpa;

    // Getters and Setters
}
