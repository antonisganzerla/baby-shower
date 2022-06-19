package com.sgztech.service;

import java.util.List;

public interface EntityService<T> {

    T save(T entity);

    T getById(Integer id);

    void update(Integer id, T entity);

    void delete(Integer id);

    List<T> find(T filter);
}
