package com.sgztech.domain.repository;

import com.sgztech.domain.entity.BabyEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BabyEventRepository extends JpaRepository<BabyEvent, Integer> {
}
