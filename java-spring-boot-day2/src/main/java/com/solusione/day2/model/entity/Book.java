package com.solusione.day2.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.hyvercode.solusione.helpers.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
@SQLDelete(sql = "UPDATE books SET deleted_at=NOW() where id=?")
@Where(clause = "deleted_at is NULL")
public class Book extends BaseEntity {

    private static final long serialVersionUID = -2155753233215409928L;

    @Id
    @Column(name = "id",length = 36,nullable = false,unique = true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Column(name = "title",length = 60,nullable = false)
    private String title;

    @Column(name = "description",length = 200)
    private String description;

    @Column(name = "price",length = 20,precision = 2)
    private BigDecimal price;

    @Column(name = "discount",precision = 2)
    private BigDecimal discount;

    @Column(name = "stock",nullable = false)
    private Integer stock;

    @Column(name = "author",length = 60,nullable = false)
    private String author;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "deleted_at")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp deletedAt;
}
