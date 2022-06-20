package com.sgztech.rest.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MessageDTO {

    @NotEmpty(message = "{field.message.required}")
    private String message;

    @NotEmpty(message = "{field.sender.required}")
    private String sender;

    @NotNull(message = "{field.eventId.required}")
    private Integer eventId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }
}
