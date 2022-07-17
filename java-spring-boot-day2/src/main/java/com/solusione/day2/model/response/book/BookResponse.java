package com.solusione.day2.model.response.book;

import com.hyvercode.common.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponse extends BaseResponse {

    private String id;
    private String title;
    private String description;
    private BigDecimal price;
    private BigDecimal discount;
    private Integer stock;
    private String author;
    private Boolean active;
}
