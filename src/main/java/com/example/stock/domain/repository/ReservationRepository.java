package com.example.stock.domain.repository;

import java.util.Optional;

import com.example.stock.domain.model.reservation.Reservation;
import com.example.stock.domain.model.reservation.ReservationId;

public interface ReservationRepository {
    Optional<Reservation> findByReservationId(ReservationId id);
    void save(Reservation reservation);
}
