package com.example.tastezip.model;


import com.example.tastezip.model.type.Evaluation;
import com.example.tastezip.model.type.Meal;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Entity
public class Diary {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String title;
    private LocalDate eatDate;

    private Meal meal;

    @ManyToOne
    private Restaurant restaurant;

    @Column(length = 500)
    private String content;

    private Evaluation evaluation;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    protected Diary(){

    }

    public Diary(String title, LocalDate eatDate, Meal meal, Restaurant restaurant, String content, Evaluation evaluation) {
        // ID 값에 대해 따로 명시하지 않아도 자동으로 생성되는지 -- GeneratedValue와 연관됨.
        this.title = title;
        this.eatDate = eatDate;
        this.meal = meal;
        this.restaurant = restaurant;
        this.content = content;
        this.evaluation = evaluation;
        this.createdAt = ZonedDateTime.now();
        this.updatedAt = ZonedDateTime.now();
    }

    public void changeDiaryInfo(String title, LocalDate eatDate, Meal meal, Restaurant restaurant, String content, Evaluation evaluation) {
        this.title = title;
        this.eatDate = eatDate;
        this.meal = meal;
        this.restaurant = restaurant;
        this.content = content;
        this.evaluation = evaluation;
        this.updatedAt = ZonedDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getEatDate() {
        return eatDate;
    }

    public Meal getMeal() { return meal; }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public String getContent() {
        return content;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }
}
