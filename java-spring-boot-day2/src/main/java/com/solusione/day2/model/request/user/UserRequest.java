package com.solusione.day2.model.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hyvercode.solusione.helpers.base.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest extends BaseRequest {
    @NotEmpty
    @Size(max = 30)
    @JsonProperty(required = true)
    @Email(message = "Customer email should be valid")
    private String email;

    @JsonProperty(required = true)
    @NotEmpty
    @Size(max = 255)
    private String password;

    @NotEmpty
    @Size(max = 1)
    private Boolean active;
}
