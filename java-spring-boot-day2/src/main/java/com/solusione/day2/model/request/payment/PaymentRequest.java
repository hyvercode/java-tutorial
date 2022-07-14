package com.solusione.day2.model.request.payment;

import com.hyvercode.solusione.helpers.base.BaseRequest;
import com.solusione.day2.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest extends BaseRequest {

    @Size(max = 36)
    @NotNull
    @NotEmpty
    private String orderId;

    @NotNull
    @NotEmpty
    private Status status;

    @NotNull
    @NotEmpty
    private BigDecimal amount;

}
