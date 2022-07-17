package com.solusione.day2.model.response.book;

import com.hyvercode.common.base.BasePaginationResponse;
import com.hyvercode.common.model.Pagination;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListBookResponse extends BasePaginationResponse {
    private Set<BookResponse> content;
    private Pagination pagination;
}
