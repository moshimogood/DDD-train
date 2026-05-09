package com.example.stock.domain.model.reservation;

import com.example.stock.domain.model.stock.ProductId;

public class Reservation {
    
    private final ReservationId reservationId;
    private final ProductId productId;
    private final int quantity;
    private ReservationStatus status;

    public Reservation(ReservationId reservationId, ProductId productId, int quantity) {
        this.reservationId = reservationId;
        this.productId = productId;
        this.quantity = quantity;
        this.status = ReservationStatus.RESERVED;
    }

    public void cancel() {
        if (status == ReservationStatus.CANCELED) {
            throw new IllegalStateException("既にキャンセル済みです");
        }

        this.status = ReservationStatus.CANCELED;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public ReservationId getReservationId() {
        return this.reservationId;
    }

    public ProductId getProductId() {
        return this.productId;
    }

    public ReservationStatus getStatus() {
        return this.status;
    }
}
