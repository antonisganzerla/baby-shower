package com.sgztech.rest.dto;

import com.sgztech.domain.enums.BabySex;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class BabyDTO {

    private Integer id;

    @NotEmpty(message = "{field.name.required}")
    private String name;

    @FutureOrPresent(message = "{field.expected-date-of-birth.must-be-equal-or-greater-than-current-date}")
    private LocalDate expectedDateOfBirth;

    private BabySex sex;

    @NotNull(message = "{field.userId.required}")
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getExpectedDateOfBirth() {
        return expectedDateOfBirth;
    }

    public void setExpectedDateOfBirth(LocalDate expectedDateOfBirth) {
        this.expectedDateOfBirth = expectedDateOfBirth;
    }

    public BabySex getSex() {
        return sex;
    }

    public void setSex(BabySex sex) {
        this.sex = sex;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
