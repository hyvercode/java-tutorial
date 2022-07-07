package com.solusione.day2.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest implements Serializable {

    @Size(max = 30)
    @NotNull
    private String firstName;

    @Size(max = 30)
    @NotNull
    private String lastName;

    @Size(max = 30)
    @NotNull
    private String email;
}
