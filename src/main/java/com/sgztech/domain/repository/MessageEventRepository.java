package com.sgztech.domain.repository;

import com.sgztech.domain.entity.MessageEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageEventRepository extends JpaRepository<MessageEvent, Integer> {
}
