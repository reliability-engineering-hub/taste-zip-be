package com.example.tastezip.service;


import com.example.tastezip.api.request.CreateDiaryRequest;
import com.example.tastezip.api.request.CreateRestaurantRequest;
import com.example.tastezip.api.response.DiaryResponse;
import com.example.tastezip.api.response.RestaurantResponse;
import com.example.tastezip.model.Diary;
import com.example.tastezip.model.Restaurant;
import com.example.tastezip.repository.DiaryRepository;
import com.example.tastezip.repository.RestaurantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Diary create(
            CreateDiaryRequest request
    ){
        Restaurant restaurant = restaurantService.create(request.getRestaurant());

        Diary diary = new Diary(request.getTitle(), request.getEatDate(), restaurant, request.getContent(), request.getEvaluation());
        diaryRepository.save(diary);
        return diary;
    }

    @Transactional(readOnly = true)
    public DiaryResponse get(
            Long diaryId
    ){
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new IllegalArgumentException("없는 일기입니다."));

        Restaurant restaurant = diary.getRestaurant();
        RestaurantResponse restaurantResponse =
                new RestaurantResponse(
                        restaurant.getName(),
                        restaurant.getAddress(),
                        restaurant.getImage()
                );

        DiaryResponse diaryResponse =
                new DiaryResponse(
                        diary.getTitle(),
                        diary.getEatDate(),
                        restaurantResponse,
                        diary.getContent(),
                        diary.getEvaluation()
                );

        return diaryResponse;
    }

    @Transactional
    public void update(
            Long diaryId,
            CreateDiaryRequest request
    ){
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new IllegalArgumentException("diaryId = " + diaryId + " 없는 일기입니다."));

        CreateRestaurantRequest restaurantRequest = request.getRestaurant();
        Restaurant restaurant = diary.getRestaurant();
        restaurantService.update(restaurant.getId(),restaurantRequest);

        diary.changeDiaryInfo(
                request.getTitle(),
                request.getEatDate(),
                restaurant,
                request.getContent(),
                request.getEvaluation());

        diaryRepository.save(diary);
    }
}
