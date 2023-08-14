package com.example.tastezip.global.model;

import java.util.List;

public class PageResponse<T> {
    private final Long totalNumber;

    private final Integer page;

    private final Integer size;

    private final List<T> targets;

    public PageResponse(Long totalNumber, Integer page, Integer size, List<T> targets) {
        this.totalNumber = totalNumber;
        this.page = page;
        this.size = size;
        this.targets = targets;
    }

    public Long getTotalNumber() {
        return totalNumber;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getSize() {
        return size;
    }

    public List<T> getTargets() {
        return targets;
    }
}
