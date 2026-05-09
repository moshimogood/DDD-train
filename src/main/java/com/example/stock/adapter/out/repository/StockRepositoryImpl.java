package com.example.stock.adapter.out.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.stock.domain.model.stock.ProductId;
import com.example.stock.domain.model.stock.Stock;
import com.example.stock.domain.repository.StockRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class StockRepositoryImpl implements StockRepository {

    private final StockJpaRepository jpaRepository;

    @Override
    public Optional<Stock> findByProductId(ProductId productId) {
        return jpaRepository.findById(productId.value())
            .map(this::toDomain);
    }

    @Override
    public void save(Stock stock) {
        jpaRepository.save(toEntity(stock));
    }

    private Stock toDomain(StockEntity entity) {
        return new Stock(
            new ProductId(entity.getProductId()),
            entity.getQuantity(),
            entity.getReservedQuantity()
        );
    }

    private StockEntity toEntity(Stock stock) {
        return new StockEntity(
            stock.getProductId().value(),
            stock.getQuantity(),
            stock.getReservedQuantity()
        );
    }
}
