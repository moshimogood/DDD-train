package com.example.stock.domain.repository;

import java.util.Optional;

import com.example.stock.domain.model.stock.ProductId;
import com.example.stock.domain.model.stock.Stock;

public interface StockRepository {
    Optional<Stock> findByProductId(ProductId productId);
    void save(Stock stock);
}


