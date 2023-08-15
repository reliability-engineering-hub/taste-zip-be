package com.example.tastezip.global.request;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public record SimplePage(
        Integer page,
        Integer size
) {
    public Pageable toPageable(){
        return PageRequest.of(this.page, this.size);
    }

}
