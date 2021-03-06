package com.sgztech.service;

import com.sgztech.domain.entity.User;
import com.sgztech.domain.repository.UserRepository;
import com.sgztech.exception.AuthException;
import com.sgztech.exception.EntityNotFoundException;
import com.sgztech.rest.dto.CredentialsDTO;
import com.sgztech.security.HashUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User save(User user) {
        String encryptedPassword = HashUtils.getHashMd5(user.getPassword());
        user.setPassword(encryptedPassword);
        return repository.save(user);
    }

    @Override
    public User getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
    }

    @Override
    public void update(Integer id, User user) {
        repository.findById(id)
                .map(u -> {
                    user.setId(u.getId());
                    repository.save(user);
                    return Void.TYPE;
                }).orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
    }

    @Override
    public void delete(Integer id) {
        repository.findById(id)
                .map(u -> {
                    repository.delete(u);
                    return Void.TYPE;
                }).orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
    }

    @Override
    public List<User> find(User filter) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<User> example = Example.of(filter, matcher);
        return repository.findAll(example);
    }

    @Override
    public void auth(CredentialsDTO dto) {
        User user = repository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new AuthException("Usuário inválido"));

        if (!isPasswordMatch(dto, user)) {
            throw new AuthException("Senha inválida");
        }
    }

    private boolean isPasswordMatch(CredentialsDTO dto, User user) {
        String encryptedPassword = HashUtils.getHashMd5(dto.getPassword());
        return user.getPassword().equals(encryptedPassword);
    }
}
