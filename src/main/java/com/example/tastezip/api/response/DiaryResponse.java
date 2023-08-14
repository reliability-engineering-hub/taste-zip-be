package com.example.tastezip.api.response;

import com.example.tastezip.api.request.CreateRestaurantRequest;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class DiaryResponse {
    private String title;

    private LocalDate eatDate;

    @NotNull
    private RestaurantResponse restaurant;

    private String content;

    // TODO : enum으로 refactoring 진행
    private Long evaluation;

    public DiaryResponse(String title, LocalDate eatDate, RestaurantResponse restaurant, String content, Long evaluation) {
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

    public RestaurantResponse getRestaurant() {
        return restaurant;
    }

    public String getContent() {
        return content;
    }

    public Long getEvaluation() {
        return evaluation;
    }
}
