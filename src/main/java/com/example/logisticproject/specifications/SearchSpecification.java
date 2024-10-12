package com.example.logisticproject.specifications;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;

public class SearchSpecification {
    public static Pageable getPageable(Integer page, Integer limit) {
        return getPageable(page, limit, null);
    }
    public static Pageable getPageable(Integer page, Integer limit, String sort) {
        return getPageable(page, limit, sort, true);
    }

    public static Pageable getPageable(Integer page, Integer limit, String sort, boolean desc) {
        if (limit == null || limit == 0) {
            limit = 10;
        }
        page = Objects.requireNonNullElse(page, 0);
        if (page > 0) {
            page = page - 1;
        }
        if (sort != null && !sort.isEmpty()) {
            return PageRequest.of(page, limit, desc ? Sort.by(sort).descending() : Sort.by(sort).ascending());
        } else {
            return PageRequest.of(page, limit);
        }

    }
}
