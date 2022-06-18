package com.sgztech.domain.entity;

import com.sgztech.domain.enums.BabySex;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@Table(name = "baby")
public class Baby {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 120)
    @NotEmpty(message = "{field.name.required}")
    private String name;

    @Column(name = "expectedDateOfBirth")
    @FutureOrPresent(message = "{field.expected-date-of-birth.must-be-equal-or-greater-than-current-date}")
    private LocalDate expectedDateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    public BabySex sex;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
