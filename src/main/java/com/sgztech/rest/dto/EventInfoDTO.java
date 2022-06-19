package com.sgztech.rest.dto;

import com.sgztech.domain.entity.BabyEvent;
import com.sgztech.domain.entity.ProductEvent;
import com.sgztech.domain.enums.EventStatus;

import java.time.LocalDateTime;
import java.util.List;

public class EventInfoDTO {

    private Integer Id;
    private String name;
    private LocalDateTime date;
    private String address;
    private EventStatus status;
    private Integer userId;
    private List<BabyEvent> babies;
    private List<ProductEvent> products;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

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

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<BabyEvent> getBabies() {
        return babies;
    }

    public void setBabies(List<BabyEvent> babies) {
        this.babies = babies;
    }

    public List<ProductEvent> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEvent> products) {
        this.products = products;
    }
}
