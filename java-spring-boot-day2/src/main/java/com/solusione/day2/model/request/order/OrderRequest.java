package com.solusione.day2.model.request.order;

import com.hyvercode.solusione.helpers.base.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest extends BaseRequest {

    @Size(max = 36)
    @NotNull
    @NotEmpty
    private String bookId;

    @Size(max = 10)
    @NotNull
    @NotEmpty
    private Integer qty;
}
