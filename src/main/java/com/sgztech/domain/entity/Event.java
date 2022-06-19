package com.sgztech.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sgztech.domain.enums.EventStatus;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer Id;

    @Column(name = "name", length = 120)
    @NotEmpty(message = "{field.name.required}")
    private String name;

    @Column(name = "date")
    @FutureOrPresent(message = "{field.date.must-be-equal-or-greater-than-current-date}")
    private LocalDateTime date;

    @Column(name = "address", length = 200)
    @NotEmpty(message = "{field.address.required}")
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EventStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "event")
    private List<ProductEvent> products;

    @OneToMany(mappedBy = "event")
    private List<BabyEvent> babies;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ProductEvent> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEvent> products) {
        this.products = products;
    }

    public List<BabyEvent> getBabies() {
        return babies;
    }

    public void setBabies(List<BabyEvent> babies) {
        this.babies = babies;
    }
}
