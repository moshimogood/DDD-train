package com.example.stock.application.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.stock.domain.model.reservation.Reservation;
import com.example.stock.domain.model.reservation.ReservationId;
import com.example.stock.domain.model.stock.ProductId;
import com.example.stock.domain.model.stock.Stock;
import com.example.stock.domain.repository.ReservationRepository;
import com.example.stock.domain.repository.StockRepository;
import com.example.stock.domain.service.ReservationDomainService;

@Service
@Transactional
public class ReservationCancelUseCase {
    private final ReservationDomainService reservationDomainService;
    private final ReservationRepository reservationRepository;
    private final StockRepository stockRepository;

    public ReservationCancelUseCase(ReservationDomainService reservationDomainService, ReservationRepository reservationRepository, StockRepository stockRepository) {
        this.reservationDomainService = reservationDomainService;
        this.reservationRepository = reservationRepository;
        this.stockRepository = stockRepository;
    }

    public void execute(String reservationId, String productId) {
        Reservation reservation = reservationRepository.findByReservationId(new ReservationId(reservationId)).orElseThrow();
        Stock stock = stockRepository.findByProductId(new ProductId(productId)).orElseThrow();
        this.reservationDomainService.cancel(reservation, stock);

        stockRepository.save(stock);
        reservationRepository.save(reservation);
    }
}
