package com.solusione.day2.model.response.order;

import com.hyvercode.solusione.helpers.base.BaseResponse;
import com.solusione.day2.model.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse extends BaseResponse {

    private String id;
    private Date orderDate;
    private Book book;
    private Integer qty;
    private BigDecimal amount;

}
