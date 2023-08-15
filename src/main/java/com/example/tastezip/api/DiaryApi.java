package com.example.tastezip.api;

import com.example.tastezip.api.request.CreateDiaryRequest;
import com.example.tastezip.api.response.DiaryResponse;
import com.example.tastezip.global.model.PageResponse;
import com.example.tastezip.model.Diary;
import com.example.tastezip.service.DiaryService;
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

    // TODO : 커스텀 페이지네이션 요청 객체 구현하기
    @GetMapping("/diaries")
    public ResponseEntity<PageResponse<Diary>> getAllDiaries(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<Integer> size
    ){
        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(10));
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
