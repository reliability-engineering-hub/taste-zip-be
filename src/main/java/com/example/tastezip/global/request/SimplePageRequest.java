package com.example.tastezip.global.request;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class SimplePageRequest {
    private Integer size;

    private Integer page;

    public SimplePageRequest(){
        this.page = 0;
        this.size = 10;
    }

    public Pageable toPageable(){
        return PageRequest.of(this.page, this.size);
    }

    public Integer getSize() {
        return size;
    }

    public Integer getPage() {
        return page;
    }

    // ModelAttribute의 경우 setter가 반드시 필요하다.
    public void setSize(Integer size) {
        this.size = size;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
