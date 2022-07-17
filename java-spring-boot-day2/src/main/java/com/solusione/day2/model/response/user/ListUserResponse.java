package com.solusione.day2.model.response.user;

import com.hyvercode.common.base.BasePaginationResponse;
import com.hyvercode.common.model.Pagination;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListUserResponse extends BasePaginationResponse {
    private Set<UserResponse> content;
    private Pagination pagination;
}
