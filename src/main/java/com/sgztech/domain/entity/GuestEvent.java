package com.sgztech.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sgztech.domain.enums.EventPresence;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Table(name = "guest_event")
public class GuestEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    @JsonIgnore
    private Event event;

    @Column(name = "name", length = 200)
    @NotEmpty(message = "{field.name.required}")
    private String name;

    @Column(name = "email", length = 200)
    @Email(message = "{field.email.must-be-valid}")
    private String email;

    @Column(name = "date", length = 200)
    private LocalDateTime date;

    @Column(name = "presence", length = 200)
    @Enumerated(EnumType.STRING)
    private EventPresence presence;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public EventPresence getPresence() {
        return presence;
    }

    public void setPresence(EventPresence presence) {
        this.presence = presence;
    }
}
