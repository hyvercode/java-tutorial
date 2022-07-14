package com.solusione.day2.model.response.payment;

import com.hyvercode.solusione.helpers.base.BaseResponse;
import com.solusione.day2.model.entity.Order;
import com.solusione.day2.model.enums.Status;
import com.solusione.day2.model.response.order.OrderResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse extends BaseResponse {
    private String id;
    private Date paymentDate;
    private Status status;
    private BigDecimal amount;
    private OrderResponse order;
}
