package com.sgztech.rest.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class GuestDTO {

    @NotEmpty(message = "{field.name.required}")
    private String name;

    @Email(message = "{field.email.must-be-valid}")
    @NotNull(message = "{field.email.required}")
    private String email;

    @NotNull(message = "{field.eventId.required}")
    private Integer eventId;

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

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }
}
