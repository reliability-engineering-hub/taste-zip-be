package com.example.tastezip.api;

import com.example.tastezip.api.request.CreateDiaryRequest;
import com.example.tastezip.api.response.DiaryResponse;
import com.example.tastezip.service.DiaryService;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/diaries/{diaryId}")
    public void editDiary(
            @PathVariable Long diaryId,
            @RequestBody CreateDiaryRequest request

    ){
        diaryService.update(diaryId, request);
    }
}
