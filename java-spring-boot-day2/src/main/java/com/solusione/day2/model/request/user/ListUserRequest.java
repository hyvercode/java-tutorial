package com.solusione.day2.model.request.user;

import com.hyvercode.solusione.helpers.base.BasePaginationRequest;
import com.hyvercode.solusione.model.Pagination;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListUserRequest extends BasePaginationRequest {
    private Set<UserRequest> content;
    private Pagination pagination;
}
