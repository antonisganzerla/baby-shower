package com.sgztech.rest.dto;

import javax.validation.constraints.NotNull;

public class BabyEventDTO {

    @NotNull(message = "{field.babyId.required}")
    private Integer babyId;

    public Integer getBabyId() {
        return babyId;
    }

    public void setBabyId(Integer babyId) {
        this.babyId = babyId;
    }
}
