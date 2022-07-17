package com.solusione.day2.model.response.user;

import com.hyvercode.common.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse extends BaseResponse {
    private String id;
    private String email;
    private Boolean active;
}
