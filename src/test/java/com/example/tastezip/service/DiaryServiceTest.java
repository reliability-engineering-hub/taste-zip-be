package com.example.tastezip.service;

import com.example.tastezip.api.request.CreateDiaryRequest;
import com.example.tastezip.api.request.CreateRestaurantRequest;
import com.example.tastezip.api.response.DiaryResponse;
import com.example.tastezip.api.response.RestaurantResponse;
import com.example.tastezip.model.Diary;
import com.example.tastezip.model.Restaurant;
import com.example.tastezip.model.type.Evaluation;
import com.example.tastezip.model.type.Meal;
import com.example.tastezip.repository.DiaryRepository;
import com.example.tastezip.repository.RestaurantRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


// RESTDOCS
// @SpringBootTest - 단위 테스트에서 사용하지 않는 이유 : 모든 설정을 다 띄우기 때문에 테스트 속도가 느려진다.
@SpringBootTest
@Transactional
public class DiaryServiceTest {


    // 객체 생성을 하지 않아도 되는지 - ComponentScan, IoC 등의 개념과 연관됨.
    // 스프링이 특정 어노테이션을 읽고 이에 대해 자동으로 객체 생성
    // 이때 IoC가 아래의 어노테이션을 보고 할당 되어야 하는 값을 탐색해서 자동 할당해준다.
    // @Primary, @Bean, @Qualifier

    @Autowired
    DiaryRepository diaryRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    DiaryService diaryService;

    Restaurant restaurant;
    Diary diary;

    @BeforeEach
    public void beforeEach() {
        restaurant = new Restaurant("장꼬방", "서초구", "https://delicious");
        diary = new Diary("맛있겠다.", LocalDate.now(), Meal.BREAKFAST, restaurant, "너무 맛있었다.", Evaluation.GOOD);

        diaryRepository.save(diary);
    }

    @DisplayName("[성공] 일기를 작성한다.")
    @Test
    void createDiary(){
        // given
        CreateRestaurantRequest restaurantRequest = new CreateRestaurantRequest("gogos", "서초구", "https://delicious");
        CreateDiaryRequest diaryRequest = new CreateDiaryRequest("맛있다.", LocalDate.now(), Meal.BREAKFAST.getMeal(), restaurantRequest, "맛있었다.", Evaluation.GOOD.getEvalution());

        // when
        Diary got = diaryService.create(diaryRequest);

        // then
        Diary want = diaryRepository.findById(got.getId()).get();
        Assertions.assertThat(want.getId()).isEqualTo(got.getId());
    }

    @DisplayName("[성공] 일기 내용을 가져온다.")
    @Test
    void getDiary(){
        // given
        // diary 객체로 부터 diaryResponse 생성
        RestaurantResponse restaurantResponse = new RestaurantResponse(restaurant.getName(), restaurant.getAddress(), restaurant.getImage());
        DiaryResponse want = new DiaryResponse(diary.getTitle(), diary.getEatDate(), diary.getMeal(), restaurantResponse , diary.getContent(), diary.getEvaluation());

        // when
        DiaryResponse got  = diaryService.get(diary.getId());

        // then
        // 재귀적으로 값을 하나씩 비교함.
        Assertions.assertThat(got).usingRecursiveComparison().isEqualTo(want);
    }

    @DisplayName("[성공] 일기 내용을 삭제한다.")
    @Test
    void deleteDiary(){
        // given

        // when
        diaryService.delete(diary.getId());

        // then
        Optional<Diary> got = diaryRepository.findById(diary.getId());
        assertFalse(got.isPresent()); // null이라면, false 반환 => false인지 확인
    }
}
