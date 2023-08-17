package com.example.tastezip.api.request;

import com.example.tastezip.model.type.Evaluation;
import com.example.tastezip.model.type.Meal;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class CreateDiaryRequest {
    private String title;

    private LocalDate eatDate;

    @NotNull
    private Meal meal;

    @NotNull
    private CreateRestaurantRequest restaurant;

    private String content;

    @NotNull
    private Evaluation evaluation;

    public CreateDiaryRequest(String title, LocalDate eatDate, Integer meal, CreateRestaurantRequest restaurant, String content, Integer evaluation){
        this.title = title;
        this.eatDate = eatDate;
        this.meal = Meal.fromInteger(meal);
        this.restaurant = restaurant;
        this.content = content;
        this.evaluation = Evaluation.fromInteger(evaluation);
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getEatDate() {
        return eatDate;
    }

    public Meal getMeal() { return meal; }

    public CreateRestaurantRequest getRestaurant() {
        return restaurant;
    }

    public String getContent() {
        return content;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }
}
