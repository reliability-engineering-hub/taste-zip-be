package com.example.tastezip.api.request;

import com.example.tastezip.model.Restaurant;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.ZonedDateTime;

public class CreateDiaryRequest {
    private String title;

    private LocalDate eatDate;

    @NotNull
    private CreateRestaurantRequest restaurant;

    private String content;

    // TODO : enum으로 refactoring 진행
    private Long evaluation;

    public CreateDiaryRequest(String title, LocalDate eatDate, CreateRestaurantRequest restaurant, String content, Long evaluation){
        this.title = title;
        this.eatDate = eatDate;
        this.restaurant = restaurant;
        this.content = content;
        this.evaluation = evaluation;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getEatDate() {
        return eatDate;
    }

    public CreateRestaurantRequest getRestaurant() {
        return restaurant;
    }

    public String getContent() {
        return content;
    }

    public Long getEvaluation() {
        return evaluation;
    }
}
