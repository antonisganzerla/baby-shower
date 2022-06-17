package com.sgztech.service;

import com.sgztech.domain.entity.Baby;

import java.util.List;

public interface BabyService {

    public Baby save(Baby baby);

    Baby getById(Integer id);

    void update(Integer id, Baby baby);

    void delete(Integer id);

    List<Baby> find(Baby babyFilter);
}
