package com.sgztech.rest.controller;

import com.sgztech.rest.dto.EventDTO;
import com.sgztech.rest.dto.EventInfoDTO;
import com.sgztech.rest.dto.GuestDTO;
import com.sgztech.rest.dto.MessageDTO;
import com.sgztech.service.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    private EventServiceImpl service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody @Valid EventDTO eventDTO) {
        return service.save(eventDTO).getId();
    }

    @GetMapping("{id}")
    public EventInfoDTO getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PostMapping("{id}/guest")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveGuest(@PathVariable Integer id, @RequestBody @Valid GuestDTO guestDTO) {
        service.saveGuest(id, guestDTO);
    }

    @PostMapping("{id}/message")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveMessage(@PathVariable Integer id, @RequestBody @Valid MessageDTO messageEventDTO) {
        service.saveMessage(id, messageEventDTO);
    }

    @PatchMapping("{id}/cancel")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancel(@PathVariable Integer id) {
        service.cancel(id);
    }

    @PatchMapping("{id}/close")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void close(@PathVariable Integer id) {
        service.close(id);
    }
}
