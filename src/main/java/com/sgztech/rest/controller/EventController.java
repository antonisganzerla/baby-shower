package com.sgztech.rest.controller;

import com.sgztech.rest.dto.EventDTO;
import com.sgztech.rest.dto.EventInfoDTO;
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
}
