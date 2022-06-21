package com.sgztech.rest.controller;

import com.sgztech.domain.entity.User;
import com.sgztech.exception.AuthException;
import com.sgztech.rest.dto.CredentialsDTO;
import com.sgztech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody @Valid User user) {
        return service.save(user);
    }

    @GetMapping("{id}")
    public User getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PostMapping("/auth")
    public void auth(@RequestBody @Valid CredentialsDTO dto) {
        try {
            service.auth(dto);
        } catch (AuthException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ex.getMessage());
        }
    }
}
