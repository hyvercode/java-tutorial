package com.solusione.day2.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    @Size(max = 30)
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @Size(max = 30)
    @NotNull
    private String lastName;

    @Column(name = "email")
    @Size(max = 30)
    @NotNull
    private String email;


}
