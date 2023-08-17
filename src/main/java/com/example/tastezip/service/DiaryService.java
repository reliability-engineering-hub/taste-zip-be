package com.example.tastezip.service;


import com.example.tastezip.api.request.CreateDiaryRequest;
import com.example.tastezip.api.request.CreateRestaurantRequest;
import com.example.tastezip.api.response.DiaryResponse;
import com.example.tastezip.api.response.RestaurantResponse;
import com.example.tastezip.global.model.PageResponse;
import com.example.tastezip.model.Diary;
import com.example.tastezip.model.Restaurant;
import com.example.tastezip.repository.DiaryRepository;
import com.example.tastezip.repository.RestaurantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;

    private final RestaurantService restaurantService;

    public DiaryService(
            DiaryRepository diaryRepository,
            RestaurantService restaurantService
    ) {
        this.diaryRepository = diaryRepository;
        this.restaurantService = restaurantService;
    }

    @Transactional
    public Diary create(
            CreateDiaryRequest request
    ) {
        Restaurant restaurant = restaurantService.create(request.getRestaurant());

        Diary diary = new Diary(request.getTitle(), request.getEatDate(), request.getMeal(), restaurant, request.getContent(), request.getEvaluation());
        diaryRepository.save(diary);
        return diary;
    }

    @Transactional(readOnly = true)
    public DiaryResponse get(
            Long diaryId
    ) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new IllegalArgumentException("없는 일기입니다."));

        // transaction script
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
                        diary.getMeal(),
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
    ) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new IllegalArgumentException("diaryId = " + diaryId + " 없는 일기입니다."));

        CreateRestaurantRequest restaurantRequest = request.getRestaurant();
        Restaurant restaurant = diary.getRestaurant();
        restaurantService.update(restaurant.getId(), restaurantRequest);

        diary.changeDiaryInfo(
                request.getTitle(),
                request.getEatDate(),
                request.getMeal(),
                restaurant,
                request.getContent(),
                request.getEvaluation());
    }

    @Transactional
    public void delete(
            Long diaryId
    ) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new IllegalArgumentException("없는 일기입니다."));

        diaryRepository.deleteById(diary.getId());
    }

    public PageResponse<Diary> findAll(Pageable pageable) {
        Page<Diary> diaries = diaryRepository.findAll(pageable);
        PageResponse<Diary> pageResponse = new PageResponse<>(
                diaries.getTotalElements(),
                diaries.getNumber(),
                diaries.getSize(),
                diaries.getContent());

        return pageResponse;
    }
}
