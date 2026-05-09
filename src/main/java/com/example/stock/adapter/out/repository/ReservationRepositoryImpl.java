package com.example.stock.adapter.out.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.stock.domain.model.reservation.Reservation;
import com.example.stock.domain.model.reservation.ReservationId;
import com.example.stock.domain.model.reservation.ReservationStatus;
import com.example.stock.domain.model.stock.ProductId;
import com.example.stock.domain.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepository {

    private final ReservationJpaRepository reservationJpaRepository;

    @Override
    public Optional<Reservation> findByReservationId(ReservationId id) {
        return reservationJpaRepository.findById(id.id())
            .map(this::toDomain);
    }

    @Override
    public void save(Reservation reservation) {
        reservationJpaRepository.save(toEntity(reservation));
    }

    private Reservation toDomain(ReservationEntity entity) {
        Reservation reservation = new Reservation(
            new ReservationId(entity.getReservationId()),
            new ProductId(entity.getProductId()),
            entity.getQuantity()
        );
        if (ReservationStatus.CANCELED.name().equals(entity.getStatus())) {
            reservation.cancel();
        }
        return reservation;
    }

    private ReservationEntity toEntity(Reservation reservation) {
        return new ReservationEntity(
            reservation.getReservationId().id(),
            reservation.getProductId().value(),
            reservation.getQuantity(),
            reservation.getStatus().name()
        );
    }
}

