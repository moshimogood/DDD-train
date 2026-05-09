package com.example.stock.adapter.out.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservations")
public class ReservationEntity {

    @Id
    private String reservationId;
    private String productId;
    private int quantity;
    private String status;

    public ReservationEntity() {
    }

    public ReservationEntity(String reservationId, String productId, int quantity, String status) {
        this.reservationId = reservationId;
        this.productId = productId;
        this.quantity = quantity;
        this.status = status;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }
}
