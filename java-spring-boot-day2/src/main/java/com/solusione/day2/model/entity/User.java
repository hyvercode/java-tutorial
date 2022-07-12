package com.solusione.day2.model.entity;

import com.hyvercode.solusione.helpers.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @Column(name = "id")
    @Size(max = 36)
    @NotNull
    @NotEmpty
    private String id;

    @Column(name = "email")
    @Size(max = 30)
    @NotNull
    @NotEmpty
    @Email
    private String email;

    @Column(name = "password")
    @Size(max = 255)
    @NotNull
    @NotEmpty
    private String password;

    @Column(name = "active")
    @Size(max = 1)
    @NotNull
    @NotEmpty
    private Boolean active;
}
