package com.example.tastezip.api;

import com.example.tastezip.api.request.CreateDiaryRequest;
import com.example.tastezip.api.response.DiaryResponse;
import com.example.tastezip.global.model.PageResponse;
import com.example.tastezip.model.Diary;
import com.example.tastezip.service.DiaryService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class DiaryApi {

    private final DiaryService diaryService;

    public DiaryApi(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @PostMapping("/diaries")
    public void createDiary(
            @RequestBody CreateDiaryRequest request
    ){
        diaryService.create(request);
    }

    @GetMapping("/diaries/{diaryId}")
    public DiaryResponse getDiary(
            @PathVariable Long diaryId
    ){
        return diaryService.get(diaryId);
    }

    @GetMapping("/diaries")
    public PageResponse<Diary> getAllDiaries(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<Integer> size
    ){
        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(10));
        return diaryService.findAll(pageable);
    }


    @PutMapping("/diaries/{diaryId}")
    public void editDiary(
            @PathVariable Long diaryId,
            @RequestBody CreateDiaryRequest request

    ){
        diaryService.update(diaryId, request);
    }

    @DeleteMapping("/diaries/{diaryId}")
    public void deleteDiary(
            @PathVariable Long diaryId

    ){
        diaryService.delete(diaryId);
    }
}
