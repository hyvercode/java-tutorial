package com.solusione.day2.model.request.user;

import com.hyvercode.solusione.helpers.base.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest extends BaseRequest {
    @NotEmpty
    @Size(max = 30)
    private String email;

    @NotEmpty
    @Size(max = 255)
    private String password;

    @NotEmpty
    @Size(max = 1)
    private Boolean active;
}
