package com.solusione.day2.model.response.order;

import com.hyvercode.solusione.helpers.base.BaseResponse;
import com.solusione.day2.model.entity.Book;
import com.solusione.day2.model.response.book.BookResponse;
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
    private BookResponse bookResponse;
    private Integer qty;
    private BigDecimal amount;

}
