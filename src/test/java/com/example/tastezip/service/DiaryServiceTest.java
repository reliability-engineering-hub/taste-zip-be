package com.example.tastezip.service;

import com.example.tastezip.api.request.CreateDiaryRequest;
import com.example.tastezip.api.request.CreateRestaurantRequest;
import com.example.tastezip.model.Diary;
import com.example.tastezip.model.Restaurant;
import com.example.tastezip.model.type.Evaluation;
import com.example.tastezip.model.type.Meal;
import com.example.tastezip.repository.DiaryRepository;
import com.example.tastezip.repository.RestaurantRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


// RESTDOCS
// @SpringBootTest - 단위 테스트에서 사용하지 않는 이유 : 모든 설정을 다 띄우기 때문에 테스트 속도가 느려진다.
@SpringBootTest
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


    @DisplayName("[성공] 일기를 작성한다.")
    @Test
    void createDiary(){
        //given
        CreateRestaurantRequest restaurantRequest = new CreateRestaurantRequest("gogos", "서초구", "https://delicious");
        CreateDiaryRequest diaryRequest = new CreateDiaryRequest("맛있다.", LocalDate.now(), Meal.BREAKFAST.getMeal(), restaurantRequest, "맛있었다.", Evaluation.GOOD.getEvalution());

        // when
        Diary diary = diaryService.create(diaryRequest);

        // then
        Diary findDiary = diaryRepository.findById(diary.getId()).get();
        Assertions.assertThat(findDiary.getId()).isEqualTo(diary.getId());
    }
}
