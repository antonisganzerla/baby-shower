package com.sgztech.rest.controller;

import com.sgztech.domain.entity.Product;
import com.sgztech.domain.entity.User;
import com.sgztech.security.HashUtils;
import com.sgztech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody @Valid User user) {
        String encryptedPassword = HashUtils.getHashMd5(user.getPassword());
        user.setPassword(encryptedPassword);
        return service.save(user);
    }

    @GetMapping("{id}")
    public User getById(@PathVariable Integer id) {
        return service.getById(id);
    }
}
