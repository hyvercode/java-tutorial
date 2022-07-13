package com.solusione.day2.model.response.book;

import com.hyvercode.solusione.helpers.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.sql.Timestamp;

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
