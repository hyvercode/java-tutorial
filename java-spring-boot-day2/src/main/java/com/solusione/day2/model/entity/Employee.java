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
    private static final long serialVersionUID = -2155753233215409928L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "first_name",length = 30,nullable = false)
    private String firstName;

    @Column(name = "last_name",length = 30,nullable = false)
    private String lastName;

    @Column(name = "email",length = 30,nullable = false,unique = true)
    private String email;


}
