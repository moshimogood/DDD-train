package com.example.stock.adapter.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockJpaRepository extends JpaRepository<StockEntity, String> {
}
