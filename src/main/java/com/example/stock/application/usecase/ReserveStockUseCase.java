package com.example.stock.application.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.stock.domain.model.reservation.Reservation;
import com.example.stock.domain.model.stock.ProductId;
import com.example.stock.domain.model.stock.Stock;
import com.example.stock.domain.repository.ReservationRepository;
import com.example.stock.domain.repository.StockRepository;
import com.example.stock.domain.service.ReservationDomainService;

@Service
@Transactional
public class ReserveStockUseCase {

    private final StockRepository stockRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationDomainService reservationDomainService;

    public ReserveStockUseCase(StockRepository stockRepository, ReservationRepository reservationRepository,
            ReservationDomainService reservationDomainService) {
        this.stockRepository = stockRepository;
        this.reservationRepository = reservationRepository;
        this.reservationDomainService = reservationDomainService;
    }

    public void execute(String productId, int quantity) {
        ProductId id = new ProductId(productId);
        Stock stock = stockRepository.findByProductId(id).orElseThrow();

        Reservation reservation = reservationDomainService.reserve(stock, id, quantity);

        stockRepository.save(stock);
        reservationRepository.save(reservation);
    }
}
