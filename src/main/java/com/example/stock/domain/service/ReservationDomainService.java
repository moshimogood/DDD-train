package com.example.stock.domain.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.stock.domain.model.reservation.Reservation;
import com.example.stock.domain.model.reservation.ReservationId;
import com.example.stock.domain.model.stock.ProductId;
import com.example.stock.domain.model.stock.Stock;

@Service
public class ReservationDomainService {
    public Reservation reserve(Stock stock, ProductId productId, int quantity) {
        stock.reserve(quantity);
        return new Reservation(new ReservationId(UUID.randomUUID().toString()), productId, quantity);
    }

    public void cancel(Reservation reservation, Stock stock) {
        reservation.cancel();
        stock.release(reservation.getQuantity());
    }
}
