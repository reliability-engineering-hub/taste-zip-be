package com.example.tastezip.api.response;

import com.example.tastezip.model.type.Evaluation;
import com.example.tastezip.model.type.Meal;
import java.time.LocalDate;

public class DiaryResponse {
    private String title;

    private LocalDate eatDate;

    private Integer meal;

    private RestaurantResponse restaurant;

    private String content;

    private Integer evaluation;

    public DiaryResponse(String title, LocalDate eatDate, Meal meal, RestaurantResponse restaurant, String content, Evaluation evaluation) {
        this.title = title;
        this.eatDate = eatDate;
        this.meal = meal.getMeal();
        this.restaurant = restaurant;
        this.content = content;
        this.evaluation = evaluation.getEvalution();
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getEatDate() {
        return eatDate;
    }

    public Integer getMeal() { return meal; }

    public RestaurantResponse getRestaurant() {
        return restaurant;
    }

    public String getContent() {
        return content;
    }

    public Integer getEvaluation() {
        return evaluation;
    }
}
