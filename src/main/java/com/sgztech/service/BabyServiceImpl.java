package com.sgztech.service;

import com.sgztech.domain.entity.Baby;
import com.sgztech.domain.repository.BabyRepository;
import com.sgztech.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BabyServiceImpl implements BabyService {

    @Autowired
    private BabyRepository repository;

    @Override
    public Baby save(Baby baby) {
        return repository.save(baby);
    }

    @Override
    public Baby getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bebê não encontrado"));
    }

    @Override
    public void update(Integer id, Baby baby) {
        repository.findById(id)
                .map(b -> {
                    baby.setId(b.getId());
                    repository.save(baby);
                    return b;
                }).orElseThrow(() -> new EntityNotFoundException("Bebê não encontrado"));
    }

    @Override
    public void delete(Integer id) {
        repository.findById(id)
                .map(b -> {
                    repository.delete(b);
                    return Void.TYPE;
                }).orElseThrow(() -> new EntityNotFoundException("Bebê não encontrado"));
    }

    @Override
    public List<Baby> find(Baby babyFilter) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING );

        Example<Baby> example = Example.of(babyFilter, matcher);
        return repository.findAll(example);
    }
}
