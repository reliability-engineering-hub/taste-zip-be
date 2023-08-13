package com.example.tastezip.service;


import com.example.tastezip.api.request.CreateDiaryRequest;
import com.example.tastezip.api.request.CreateRestaurantRequest;
import com.example.tastezip.model.Diary;
import com.example.tastezip.model.Restaurant;
import com.example.tastezip.repository.DiaryRepository;
import org.springframework.stereotype.Service;

@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;

    private final RestaurantService restaurantService;

    public DiaryService(
            DiaryRepository diaryRepository,
            RestaurantService restaurantService
    ){
        this.diaryRepository = diaryRepository;
        this.restaurantService = restaurantService;
    }

    public Diary create(
            CreateDiaryRequest request
    ){
        Restaurant restaurant = restaurantService.create(request.getRestaurant());

        Diary diary = new Diary(request.getTitle(), request.getEatDate(), restaurant, request.getContent(), request.getEvaluation());
        diaryRepository.save(diary);
        return diary;
    }
}
