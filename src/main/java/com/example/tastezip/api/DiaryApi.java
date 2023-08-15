package com.example.tastezip.api;

import com.example.tastezip.api.request.CreateDiaryRequest;
import com.example.tastezip.api.response.DiaryResponse;
import com.example.tastezip.global.model.PageResponse;
import com.example.tastezip.global.request.SimplePage;
import com.example.tastezip.global.request.SimplePageRequest;
import com.example.tastezip.model.Diary;
import com.example.tastezip.service.DiaryService;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class DiaryApi {

    private final DiaryService diaryService;

    public DiaryApi(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @PostMapping("/diaries")
    public ResponseEntity<Void> createDiary(
            @RequestBody CreateDiaryRequest request
    ){
        diaryService.create(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/diaries/{diaryId}")
    public ResponseEntity<DiaryResponse> getDiary(
            @PathVariable Long diaryId
    ){
        return new ResponseEntity<>(diaryService.get(diaryId), HttpStatus.OK);
    }

    @GetMapping ("/diaries")
    public ResponseEntity<PageResponse<Diary>> getAllDiaries(
          @ModelAttribute SimplePage simplePage
    ){
        Pageable pageable = simplePage.toPageable();
        return new ResponseEntity<>(diaryService.findAll(pageable), HttpStatus.OK);
    }


    @PutMapping("/diaries/{diaryId}")
    public ResponseEntity<Void> editDiary(
            @PathVariable Long diaryId,
            @RequestBody CreateDiaryRequest request

    ){
        diaryService.update(diaryId, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/diaries/{diaryId}")
    public ResponseEntity<Void> deleteDiary(
            @PathVariable Long diaryId

    ){
        diaryService.delete(diaryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
