package com.Umair.questionservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String question;

    @NotEmpty
    private String option1;

    @NotEmpty
    private String option2;

    @NotEmpty
    private String option3;

    @NotEmpty
    private String option4;

    @NotEmpty
    private String answer;

    @NotEmpty
    private String category;

    private String difficulty;
}
