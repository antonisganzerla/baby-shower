package com.sgztech.domain.repository;

import com.sgztech.domain.entity.ProductEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductEventRepository extends JpaRepository<ProductEvent, Integer> {
}
