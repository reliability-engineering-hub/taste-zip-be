package com.example.tastezip.api.response;

import com.example.tastezip.api.request.CreateRestaurantRequest;
import com.example.tastezip.model.type.Evaluation;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class DiaryResponse {
    private String title;

    private LocalDate eatDate;

    private RestaurantResponse restaurant;

    private String content;

    private Integer evaluation;

    public DiaryResponse(String title, LocalDate eatDate, RestaurantResponse restaurant, String content, Evaluation evaluation) {
        this.title = title;
        this.eatDate = eatDate;
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
