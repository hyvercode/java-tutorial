package com.solusione.day2.model.request.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hyvercode.common.base.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest extends BaseRequest {

    @Size(max = 60)
    @NotNull
    @NotEmpty
    @JsonProperty(required = true)
    private String title;

    @Size(max = 200)
    @NotNull
    @NotEmpty
    private String description;

    @NotNull
    @NotEmpty
    @Positive
    private BigDecimal price;

    @NotNull
    @NotEmpty
    @Positive
    private BigDecimal discount;

    @NotNull
    @NotEmpty
    @Positive
    private Integer stock;

    @Size(max = 60)
    @NotNull
    @NotEmpty
    private String author;

    @NotNull
    @NotEmpty
    private Boolean active;
}
