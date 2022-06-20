package com.sgztech.domain.repository;

import com.sgztech.domain.entity.GuestEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestEventRepository extends JpaRepository<GuestEvent, Integer> {
}
