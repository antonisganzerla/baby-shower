package com.sgztech.service;

import com.sgztech.domain.entity.User;
import com.sgztech.rest.dto.CredentialsDTO;

public interface UserService extends EntityService<User> {
    void auth(CredentialsDTO dto);
}
