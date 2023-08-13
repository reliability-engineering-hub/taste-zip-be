package com.example.tastezip.api;

import com.example.tastezip.api.request.CreateDiaryRequest;
import com.example.tastezip.service.DiaryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
