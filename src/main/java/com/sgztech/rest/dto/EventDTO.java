package com.sgztech.rest.dto;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class EventDTO {

    @NotEmpty(message = "{field.name.required}")
    private String name;

    @FutureOrPresent(message = "{field.date.must-be-equal-or-greater-than-current-date}")
    private LocalDateTime date;

    @NotEmpty(message = "{field.address.required}")
    private String address;

    @NotNull(message = "{campo.userId.required}")
    private Integer userId;

    @NotNull(message = "{campo.products.required}")
    @NotEmpty(message = "{campo.products.cannot-be-empty}")
    private List<ProductEventDTO> products;

    @NotNull(message = "{campo.babies.required}")
    @NotEmpty(message = "{campo.babies.cannot-be-empty}")
    private List<BabyEventDTO> babies;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<ProductEventDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEventDTO> products) {
        this.products = products;
    }

    public List<BabyEventDTO> getBabies() {
        return babies;
    }

    public void setBabies(List<BabyEventDTO> babies) {
        this.babies = babies;
    }
}
