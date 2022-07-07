package com.solusione.day2.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
